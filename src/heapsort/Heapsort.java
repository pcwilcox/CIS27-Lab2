package heapsort;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Pete Wilcox on 3/20/2016.
 * petercwilcox@gmail.com
 */
public class Heapsort
{
    public static void main(String[] args)
    {
        MaxHeap            test = new MaxHeap();
        Random             rand = new Random();
        ArrayList<Integer> keys = new ArrayList<>();
        while (keys.size() < 100)
        {
            int i = rand.nextInt(500);
            if (!keys.contains(i))
            {
                keys.add(i);
            }
        }

        while (!keys.isEmpty())
        {
            int j = keys.remove(0);
            System.out.println("Adding " + j);
            test.insert(j);
        }

        System.out.println(test);
        System.out.println();
        test.heapSort();
        System.out.println(test);

    }
}

/* Program output:
Adding 218
// I omitted a ton of lines here
Adding 127

// Initial array, heapified
499 495 481 494 443 469 493 331 471 425 458 485 352 268
439 371 420 452 431 436 364 369 125 158 258 296 398 414
346 388 254 378 405 300 27 49 310 229 290 143 46 3 182
21 78 120 221 126 192 80 370 119 341 141 203 366 292
248 29 206 282 212 105 152 44 235 273 8 82 59 62 121
156 33 135 209 91 240 253 189 305 222 18 317 142 35 195
242 151 365 25 118 252 12 50 269 147 218 233 127

// Array after the heapsort
3 8 12 18 21 25 27 29 33 35 44 46 49 50 59 62 78 80 82
91 105 118 119 120 121 125 126 127 135 141 142 143 147
151 152 156 158 182 189 192 195 203 206 209 212 218 221
222 229 233 235 240 242 248 252 253 254 258 268 269 273
282 290 292 296 300 305 310 317 331 341 346 352 364 365
366 369 370 371 378 388 398 405 414 420 425 431 436 439
443 452 458 469 471 481 485 493 494 495 499

 */