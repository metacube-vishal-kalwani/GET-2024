/*
 * Assigment2- software construction and fundamentals
 * auther - vishal kalwani GET Oct 2024
 */
package Assigments;

import java.util.ArrayList;
import java.util.Scanner;

/* Job class contains the information of a single job */
class Job {

    public int arrivalTime;
    public int burstTime;
    public int waitingTime;
    public int completionTime;
    public int turnAroundTime;

    /* constructor to initialize a job with arrival and burst time */
    Job(int arrival, int burst) {
        this.arrivalTime = arrival;
        this.burstTime = burst;
    }
};

/*
 * JobSchedular class maintains the list of jobs and also have its related
 * methods
 */
class JobSchedular {

    private static ArrayList<Job> joblist = new ArrayList<>(); // list of jobs
    public static int currentTime = 0;

    void AddJobs(int arrival, int burst) {
        Job job = new Job(arrival, burst);
        currentTime = Math.max(currentTime, job.arrivalTime);
        currentTime += job.burstTime;
        job.completionTime = currentTime;
        job.turnAroundTime = job.completionTime - job.arrivalTime;
        job.waitingTime = job.turnAroundTime - job.burstTime;
        joblist.add(job);
    }

    /* Function to calculate completion time of each process */
    int CompletionTime(int index) {
        if (joblist.size() == 0) {
            System.out.println("No jobs for now!");
            return -1;
        }

        return joblist.get(index).completionTime;
    }

    /* Function to calculate turn around time time of each process */
    int TurnAroundTime(int index) {
        if (joblist.size() == 0) {
            return -1;
        }
        return joblist.get(index).turnAroundTime;

    }

    int WaitingTime(int index) {
        if (joblist.size() == 0) {
            return -1;
        }
        return joblist.get(index).waitingTime;
    }

    /* Function to calculate average waiting time */
    float AverageWaitingTime() {
        float totalWaitingTime = 0;
        for (int i = 0; i < joblist.size(); i++) {
            totalWaitingTime += (float) joblist.get(i).waitingTime;
        }
        return totalWaitingTime / joblist.size();
    }

    /* Function to maximum waiting time */
    float MaxWaitingTime() {
        float maxWaitingTime = 0;
        for (int i = 0; i < joblist.size(); i++) {
            maxWaitingTime = Math.max(maxWaitingTime, joblist.get(i).waitingTime);
        }
        return maxWaitingTime;
    }

    void showJobDetails() {
        System.out.println("Arrival" + "\t\t" + "Burst" + "\t\t" + "Completion" + "\t"
                + "Turn around " + "\t" + "Waiting");
        for (int i = 0; i < joblist.size(); i++) {
            Job current = joblist.get(i);
            System.out
                    .println(current.arrivalTime + "\t\t" + current.burstTime + "\t\t" + current.completionTime + "\t\t"
                            + current.turnAroundTime + "\t\t" + current.waitingTime);

        }
    }
};

public class Third {
    public static void main(String args[]) {

        JobSchedular j = new JobSchedular();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of jobs : ");
        System.out.println();
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival and burst time of job " + i + ": ");
            int arr = sc.nextInt();
            int burst = sc.nextInt();
            j.AddJobs(arr, burst);
        }
        sc.close();

        j.showJobDetails();
        System.out.println(j.CompletionTime(2));
        System.out.println(j.TurnAroundTime(3));
        System.out.println(j.WaitingTime(0));
        System.out.println(j.AverageWaitingTime());

    }
}
