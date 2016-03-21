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
            System.out.println("Job #" + ++jobCount + ": " +
                               " Waiting time: " + lapsed +
                               " Processing Time: " + i);
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
            System.out.println("Job #" + ++jobCount + ": " +
                               " Waiting time: " + lapsed +
                               " Processing Time: " + i);
            lapsed += i;
        }

        lapsed = 0;
        jobCount = 0;
        System.out.println("Third Test - Round Robin:");

        // This one uses an arraylist with a simple loop on it
        ArrayList<Integer> jobList = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            jobList.add(jobs[i]);
        }

        while (!jobList.isEmpty())
        {
            int i = jobList.remove(0);
            System.out.println("Job #" + ++jobCount + ": " +
                               " Waiting time: " + lapsed +
                               " Processing Time: " + i);
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

/* Program output: (edited formatting a bit)
Program to test priority queue systems.
100 random jobs:
89 47 27 75 32 20 32 100 85 18 8 23 20 39 100 32 36 18
44 61 37 50 49 35 39 65 29 13 93 83 54 66 4 86 71 61 90
90 94 37 47 85 75 54 37 99 99 2 96 74 56 58 13 12 71 31
29 76 74 71 99 71 55 80 73 6 26 31 82 69 57 13 16 91 79
37 14 56 85 37 13 62 12 55 82 29 76 2 86 23 16 76 94 65
44 13 56 87 2 0

First test - First-In-First-Out queue:
Job #1:  Waiting time: 0 Processing Time: 89
// I'm omitting the jobs in the middle here
Job #100:  Waiting time: 5181 Processing Time: 0

Second Test - Shortest-Job-First:
Job #1:  Waiting time: 0 Processing Time: 0
// Same here
Job #100:  Waiting time: 5081 Processing Time: 100

Third Test - Round Robin:
Job #1:  Waiting time: 0 Processing Time: 89
Job #100:  Waiting time: 5181 Processing Time: 0
// First time through

Job #1:  Waiting time: 5181 Processing Time: 69
Job #100:  Waiting time: 9206 Processing Time: 54
// Second!

Job #1:  Waiting time: 9260 Processing Time: 7
Job #100:  Waiting time: 11609 Processing Time: 6
// Third!

Job #1:  Waiting time: 11615 Processing Time: 14
Job #2:  Waiting time: 11629 Processing Time: 7
// Finally done

 */