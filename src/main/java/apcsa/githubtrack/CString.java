package apcsa.githubtrack;

public class CString implements CStringUtil //implementing the CStringUtil Interface; the CString class
{
    private String word;
    private String[] array;

    public CString(String w) //initializing a CString
    {
        array = new String[w.length()]; //setting a String array to the String parameter's length
        word = w;
        for (int i = 0; i < word.length()-1; i++)
        {
            array[i] = word.substring(i, i+1); //taking a String, and making an array, with each item within the array being a letter from the String, preserving order
        }
        array[word.length()-1] = word.substring(word.length()-1); //setting the final character in the String to the array's final position
    }

    public void reverse() //reverses the CString
    {
        String[] temp = new String[array.length];
        for (int i = 0; i < array.length; i++)
        {
            temp[i] = array[array.length-1-i]; //setting every value in array to the "opposite" side of temp, a temporary array of the same length as array
        }
        array = temp;

    }

    public void sortAscending() //sorting array in ascending order, implementing Selection Sort
    {
        for (int j = 0; j < array.length -1; j++)
        {
            int minimumIndex = j; //setting the minimum index
            for (int k = j+1; k < array.length; k++)
            {
                if (array[k].compareTo(array[minimumIndex]) < 0) //if the letter at k is less (before) the letter at the current minimumIndex
                {
                    minimumIndex = k; //k is now the new minimumIndex
                }
            }
            String temp = array[j]; //setting the value at j to the value at the index of the minimum
            array[j] = array[minimumIndex];
            array[minimumIndex] = temp; //setting the value at the index of the minimum to the value at the index of j

        }

    }
    public void sortDescending() //sorting the array in descending order using Insertion Sort
    {
        for (int j = 1; j < array.length; j++)
        {
            String temp = array[j]; //setting a temporary String for the value at j so that we don't lose it
            int possibleIndex = j; 
            while (possibleIndex > 0 && temp.compareTo(array[possibleIndex-1]) < 0) // while the value at j is "lower" than the value at possibleIndex, and possibleIndex is greater than 0
            {
                array[possibleIndex] = array[possibleIndex-1]; //shift the elements to the right
                possibleIndex--; //decrement possibleIndex
            }
            array[possibleIndex] = temp; //set the value at possibleIndex to the value at j, then loop through again
        }
    }

    public String[] getArray() //getter method
    {
        return array; //returning the array
    }

    public String toString() //toString method: basically an inverse of the constructor: takes the array character by character, and forms a word out of it
    {
        String newword = "";
        for (int i = 0; i < array.length; i++)
        {
            newword = newword + array[i];
        }
        return newword;
    }
}
