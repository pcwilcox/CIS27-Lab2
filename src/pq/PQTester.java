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
        double turnaround = 0;
        double average = 0;
        int jobCount = 0;

        // Array with 100 random jobs in it for testing

        int[] jobs = new int[100];
        for (int i = 0; i < 100; i++)
        {
            int j = rand.nextInt(101);
            jobs[i] = j;
            System.out.print(j + " ");
        }

        System.out.println("First test - First-In-First-Out queue:");
        Queue<Integer> jobQueue = new Queue<>();
        for (int i = 0; i < 100; i++)
        {
            jobQueue.enqueue(jobs[i]);
        }


        while (!jobQueue.isEmpty())
        {
            int i = jobQueue.dequeue();
            turnaround += i;
            average += turnaround;

        }
        average /= 100;
        System.out.println("Average turnaround: " + average);
        turnaround = 0;
        average = 0;
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
            turnaround += i;
            average += turnaround;
        }

        average /= 100;

        System.out.println("Average turnaround: " + average);
        turnaround = 0;
        average = 0;
        System.out.println("Third Test - Round Robin:");

        // This one uses an arraylist with a simple loop on it.
        // We'll run this test for time slices from 5-50
        for (int j = 5; j < 55; j += 5)
        {
            System.out.println("J: " + j);
            ArrayList<Integer> jobList = new ArrayList<>();
            for (int i = 0; i < 100; i++)
            {
                jobList.add(jobs[i]);
            }

            while (!jobList.isEmpty())
            {
                int i = jobList.remove(0);

                if (i > j)
                {
                    turnaround += i - j;
                    average += turnaround;
                    i -= j;
                    jobList.add(i);
                }
                else
                {
                    turnaround += i;
                    average += turnaround;
                }
            }
            average /= 100;
            System.out.println("Average turnaround: " + average);
            average = 0;
            turnaround = 0;
        }
    }
}

/* Program output: (edited formatting a bit)
80 28 63 41 73 76 38 69 57 3 80 6 22 55 33
78 0 72 24 46 83 58 6 58 60 44 52 51 91 95
89 81 48 30 27 97 49 31 57 50 55 2 90 47
78 26 92 17 57 86 95 54 92 93 42 20 2 50
59 92 29 30 66 65 82 62 3 5 91 77 98 75 95
15 1 100 62 14 50 53 1 23 3 27 89 63 63 74
15 41 9 5 27 52 93 78 49 90 17 81

First test - First-In-First-Out queue:
Average turnaround: 2665.42

Second Test - Shortest-Job-First:
Average turnaround: 1782.83

Third Test - Round Robin:
J: 5  Average turnaround: 227351.14
J: 10 Average turnaround: 57044.19
J: 15 Average turnaround: 25801.89
J: 20 Average turnaround: 14341.98
J: 25 Average turnaround: 9392.02
J: 30 Average turnaround: 6888.61
J: 35 Average turnaround: 5154.78
J: 40 Average turnaround: 4216.59
J: 45 Average turnaround: 3523.72
J: 50 Average turnaround: 2966.5

Unsurprisingly, the SJF goes fastest and the
higher the time-slice, the faster the round robin goes.
 */