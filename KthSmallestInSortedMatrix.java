// Time Complexity : O(n * log(max - min)), Binary search over the value range max and min no, for each mid, counting less-than-equal values takes O(n).
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Approach -
//   - Perform a binary search on the value range, not on indices.
//   - For a guessed value `mid`, count how many elements are ≤ mid.
//   - If the count is less than k, search in the higher half of the range.
//   - Otherwise, search in the lower half.
//   - Use a helper function that starts from the bottom-left and counts elements ≤ mid,
//     while also tracking the closest smaller and larger values.

public class KthSmallestInSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0];
        int high = matrix[n-1][n-1];
        while(low < high) { //run till low and high cross
            int mid = low + (high - low) / 2;
            //create small large pair array
            int[] smallLargePair = new int[]{matrix[0][0], matrix[n-1][n-1]};
            //get no. of less equal numbers than mid
            int count = this.countLessEqualNumbers(matrix, mid, smallLargePair);
            //3 cases based on count value and k
            if(count == k) {
                return smallLargePair[0];
            } else if(count < k) {
                low = smallLargePair[1];    //search higher
            } else {
                high = smallLargePair[0];   //search lower
            }
        }
        return low;
    }

    private int countLessEqualNumbers(int[][] matrix, int mid, int[] smallLargePair) {
        int count = 0;
        int n = matrix.length;
        //start counting from bottom left corner and go to top right.
        int row = n - 1, col = 0;

        while(row >= 0 && col < n) {
            if(matrix[row][col] > mid) {    //if no is greater than mid no in range then keep track of large no. for next iteration
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
                row--;  //go to previous row
            } else {
                //no is less or equal to mid, then count+= row + 1 and keep track of biggest no in numbers smaller than k
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
                count += row + 1;
                col++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        KthSmallestInSortedMatrix solver = new KthSmallestInSortedMatrix();
        int[][] matrix1 = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };
        int k1 = 8;
        System.out.println("Kth smallest (Test Case 1): " + solver.kthSmallest(matrix1, k1)); // Expected: 13

        int[][] matrix2 = {
                {1, 2},
                {1, 3}
        };
        int k2 = 2;
        System.out.println("Kth smallest (Test Case 2): " + solver.kthSmallest(matrix2, k2)); // Expected: 1
    }
}
