// Time Complexity : O(n log n + n log n), sorting intervals and insert/remove intervals from min-heap
// Space Complexity : O(n), min-heap will contain n at worst case
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Approach -
//   - Sort all meetings by start time.
//   - Use a min-heap to keep track of end times of meetings currently using rooms.
//   - For each meeting:
//       • If the room due to free earliest is free (heap.peek() <= current start), reuse it (heap.poll()).
//       • Else, a new room is needed.
//       • Add current meeting's end time to the heap.
//   - The size of the heap at the end gives the minimum number of rooms required.

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRooms2 {
    // TC = O(nlogn) SC = O(n)
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);   //sort array based on starting time
        PriorityQueue<Integer> meetingRooms = new PriorityQueue<>((a, b) -> a - b);    //min-heap for end time of meetings
        meetingRooms.add(intervals[0][1]);  //adding first element's end index

        for(int i = 1; i < intervals.length; i++) {
            if(meetingRooms.peek() <= intervals[i][0]) {
                meetingRooms.poll();
            }

            meetingRooms.add(intervals[i][1]);
        }

        return meetingRooms.size();
    }

    public static void main(String[] args) {
        MeetingRooms2 solver = new MeetingRooms2();

        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
        int[][] intervals2 = {{7, 10}, {2, 4}};
        System.out.println("Minimum rooms needed (Test Case 1): " + solver.minMeetingRooms(intervals1)); //expected: 2
        System.out.println("Minimum rooms needed (Test Case 2): " + solver.minMeetingRooms(intervals2)); //expected: 1
    }
}
