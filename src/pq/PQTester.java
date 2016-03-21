package pq;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Pete Wilcox on 3/20/2016.
 * petercwilcox@gmail.com
 */
public class PQTester
{
    public static void main(String[] args)
    {
        Random rand = new Random();

        System.out.println("Program to test priority queue systems.");
        System.out.println("100 random jobs: ");
        int[] jobs = new int[100];
        for (int i = 0; i < 100; i++)
        {
            int j = rand.nextInt(101);
            jobs[i] = j;
            System.out.print(j + " ");
        }
        System.out.println();
        System.out.println("First test - First-In-First-Out queue:");
        Queue<Integer> jobQueue = new Queue<>();
        for (int i = 0; i < 100; i++)
        {
            jobQueue.enqueue(jobs[i]);
        }

        int lapsed   = 0;
        int jobCount = 0;
        while (!jobQueue.isEmpty())
        {
            int i = jobQueue.dequeue();
            System.out.println("Job #" + ++jobCount + ": " + " Waiting time: " + lapsed + " Processing Time: " + i);
            lapsed += i;
        }
        lapsed = 0;
        jobCount = 0;

        MinHeap jobHeap = new MinHeap();
        for (int i = 0; i < 100; i++)
        {
            jobHeap.insert(jobs[i]);
        }
        System.out.println("Second Test - Shortest-Job-First:");
        while (!jobHeap.isEmpty())
        {
            int i = jobHeap.remove();
            System.out.println("Job #" + ++jobCount + ": " + " Waiting time: " + lapsed + " Processing Time: " + i);
            lapsed += i;
        }

        lapsed = 0;
        jobCount = 0;
        System.out.println("Third Test - Round Robin:");
        ArrayList<Integer> jobList = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            jobList.add(jobs[i]);
        }

        while (!jobList.isEmpty())
        {
            int i = jobList.remove(0);
            System.out.println("Job #" + ++jobCount + ": " + " Waiting time: " + lapsed + " Processing Time: " + i);
            lapsed += i;
            i -= 20;
            if (jobCount >= 100)
            {
                jobCount = 0;
            }
            if (i > 0)
            {
                jobList.add(i);
            }
        }
    }
}
