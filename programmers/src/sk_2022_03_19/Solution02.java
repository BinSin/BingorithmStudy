package sk_2022_03_19;

import java.util.*;

public class Solution02 {

    private static class Process implements Comparable<Process> {
        String work;
        int startTime, remainTime, A, B, C;
        boolean isLock;

        Process(String work, int startTime, int remainTime, int A, int B, int C, boolean isLock) {
            this.work = work;
            this.startTime = startTime;
            this.remainTime = remainTime;
            this.A = A;
            this.B = B;
            this.C = C;
            this.isLock = isLock;
        }

        @Override
        public int compareTo(Process o) {
            if (this.isLock) return 1;

            if (this.work.equals("read")) {
                if (o.work.equals("write")) return 1;
                else return 0;
            } else {
                if (o.work.equals("write")) return 0;
                else return -1;
            }
        }
    }

    private static Process toProcess(StringTokenizer st, boolean isLock) {
        String work = st.nextToken();
        int startTime = Integer.parseInt(st.nextToken());
        int remainTime = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = (st.hasMoreTokens()) ? Integer.parseInt(st.nextToken()) : -1;
        return new Process(work, startTime, remainTime, A, B, C, isLock);
    }

    private static void work(Map<String, Integer> map, int time, List<Process> workingProcessList, String[] arr) {
        for (Process p : workingProcessList) {
            String work = p.work;
            int remainTime = p.remainTime;
            int A = p.A;
            int B = p.B;
            int C = p.C;

            if (remainTime <= time) {
                time -= remainTime;
                p.remainTime = 0;
            } else {
                p.remainTime -= time;

            }

            map.put(arr[A], map.getOrDefault(arr[A], 0) + remainTime);
            map.put(arr[B], map.getOrDefault(arr[B], 0) + remainTime);
            if (time == 0) {
                if (work.equals("write")) {
                    arr[A] = String.valueOf(C);
                    arr[B] = String.valueOf(C);
                }
            }
        }

        if (time >= 0) {
            work(map, time, workingProcessList, arr);
        }
    }

    public static String[] solution(String[] arr, String[] processes) {
        Map<String, Integer> map = new TreeMap<>(String::compareTo);
        List<Process> workingProcessList = new LinkedList<>(); // 일하고 있는 프로세스
        PriorityQueue<Process> pq = new PriorityQueue<>(Process::compareTo); // 남아있는 프로세스

        StringTokenizer st = new StringTokenizer(processes[0]);
        Process firstProcess = toProcess(st, true);
        workingProcessList.add(firstProcess);

        for (int i=1; i< processes.length; i++) {
            StringTokenizer st2 = new StringTokenizer(processes[i]);
            Process process = toProcess(st2, false);
            pq.add(process);

            if (!workingProcessList.isEmpty()) {
                work(map, i, workingProcessList, arr); // 남은 프로세스 일시키기
            } else {
                workingProcessList.add(pq.poll()); // 일하고 있는 프로세스가 없으면 추가
            }
        }

        return map.values().stream().map(String::valueOf).toArray(String[]::new);
    }

    public static void main(String[] args) {
        String[] arr1 = {"1","2","4","3","3","4","1","5"};
        String[] processes1 = {"read 1 3 1 2","read 2 6 4 7","write 4 3 3 5 2","read 5 2 2 5","write 6 1 3 3 9", "read 9 1 0 7"};
        System.out.println(Arrays.toString(solution(arr1, processes1)));

        String[] arr2 = {"1","1","1","1","1","1","1"};
        String[] processes2 = {"write 1 12 1 5 8","read 2 3 0 2","read 5 5 1 2","read 7 5 2 5","write 13 4 0 1 3","write 19 3 3 5 5","read 30 4 0 6","read 32 3 1 5"};
        System.out.println(Arrays.toString(solution(arr2, processes2)));
    }

}
