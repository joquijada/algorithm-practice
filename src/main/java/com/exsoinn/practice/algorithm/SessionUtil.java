package com.exsoinn.practice.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given list of sessions with session ID, user id, start, end, find total time user was logged in
 * when given a range start to end.
 * Times Worked
 * 03/16/2019
 * 08:06AM - 08:52AM
 * 08:54AM - 09:16AM
 * 02:34PM - 03:20PM
 * Total: 114 minutes
 *
 * Algorithm
 * 1. Filter sessions not belonging to user in question
 * 2. Find true and unique session periods from the list of session, storing start and end times in Map respectively
 *    as key/value pairs
 * 3. Based on requested range, add up how much time each individual non-overlapping time period contributes.
 *
 * @author josequijada
 */
public class SessionUtil {


  public long totalLoggedInTime(List<Session> pSessions, long pUid, long start,
          long end) {
    List<Session> userSessions = filterByUserId(pSessions, pUid);
    return totalLoggedInTimeHelper(userSessions, start, end);
  }



  private List<Session> filterByUserId(List<Session> pSessions, long pUid) {
    List<Session> tmp = new ArrayList<>();
    for (Session s : pSessions) {
      if (s.uid != pUid) {
        continue;
      }
      tmp.add(s);
    }
    return tmp;
  }


  public long totalLoggedInTimeHelper(List<Session> pSessions, long start,
          long end) {
    long total = 0;
    /*
     * Find unique start/end times that do not overlap with each other
     */
    Map<Long, Long> withoutOverlaps = new HashMap<>();
    for (Session s : pSessions) {
      long adjustedStart = s.start;
      long adjustedEnd = s.end;
      for (Map.Entry<Long, Long> ent : withoutOverlaps.entrySet()) {
        long seenStart = ent.getKey();
        long seenEnd = ent.getValue();
        if (s.start >= seenStart && s.start <= seenEnd) {
          adjustedStart = seenEnd;
        }
        if (s.end >= seenStart && s.end <= seenEnd) {
          adjustedEnd = seenStart;
        }
      }

      // If sessions seen thus far did not reduce this session time range to 0, add it to Map
      if (adjustedEnd > adjustedStart) {
        withoutOverlaps.put(adjustedStart, adjustedEnd);
      }
    }


    // Now that we have session star/end times which do not overlap with each other, calculate how much time
    // found for the request start/end times
    for (Map.Entry<Long, Long> ent : withoutOverlaps.entrySet()) {
      long sessStart = ent.getKey();
      long sessEnd = ent.getValue();
      /*
       * Case 1: both start and end completely fall inside a session (or both start/end match session start/end exactly)
       * Case 2: start falls inside session (inclusive), but not end
       * Case 3: end falls inside session (inclusive), but not start
       */
      // start = 200
      // end   = 500
      // 100    300
      //    200              600
      //    200     400
      //    200          500
      //            400          700

      // When the range start is captured by a session, subtract range start from session end to capture
      // that time to get total time this session gives
      if (start >= sessStart && start <= sessEnd) {
        if (end >= sessStart && end <= sessEnd) { // Case 1
          total += end - start;
        } else { // Case 2
          total += sessEnd - start;
        }
      } else if (end >= sessStart && end <= sessEnd) {
        // Case 3 (if() above would have caught it if start fell inside session start/end)
        total += end - sessStart;
      }
    }

    return total;
  }


  private static class Session {

    private final long id;
    private final long uid;
    private final long start;
    private final long end;

    private Session(long pId, long pUid, long pStart, long pEnd) {
      id = pId;
      uid = pUid;
      start = pStart;
      end = pEnd;
    }


    @Override
    public boolean equals(Object other) {
      if (this == other) {
        return true;
      }

      if (!(other instanceof Session)) {
        return false;
      }

      Session otherSess = (Session) other;
      if (this.id == otherSess.id) {
        return true;
      }
      return false;
    }
  }


  public static void main(String[] pArgs) {
    SessionUtil su = new SessionUtil();
    System.out.println(su.totalLoggedInTime(buildSessions(), 1, 200, 500));
  }


  private static List<Session> buildSessions() {
    // start = 200
    // end   = 500
    // 100    300
    //    200              600
    //    200     400
    //    200          500
    //            400          700
    long uid = 1L;
    long sessId = 0;
    List<Session> l = new ArrayList<>();
    l.add(new Session(sessId++, uid, 100, 300));
    /*l.add(new Session(sessId++, uid, 200, 600));
    l.add(new Session(sessId++, uid, 200, 400));
    l.add(new Session(sessId++, uid, 200, 500));
    l.add(new Session(sessId++, uid, 400, 700));*/
    return l;
  }
}
