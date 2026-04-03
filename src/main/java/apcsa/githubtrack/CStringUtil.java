package apcsa.githubtrack;

// Implement your CStringUtil class here
public interface CStringUtil //a utility class, implemented by CString, that has static methods and values
{
    
    public static boolean isPalindrome(CString string) //a method to determine whether a CString is a Palindrome or not (is same backwards and forwards)
    {
        boolean palin = true; //the return value base case
        String[] reverse = new String[string.getArray().length];
       for (int i = 0; i < string.getArray().length; i++)
       {
        reverse[string.getArray().length-1-i] = string.getArray()[i]; //create a reversed array of the CString's array of characters
       } 
       int j = 0;
       while(j < string.getArray().length) //for every value in string
       {
          for (String str : reverse) //for every value in reverse
         {
          if (!str.equals(string.getArray()[j])) //if they are not equal
          {
              palin = false; //set return value to false
              j = string.getArray().length; //end the loop
          }
          else
          {
            j++; //otherwise, move on to the next elements of both arrays
          }
        }
       }
       return palin; //return the boolean truth value 
    }

    public static int[] toNumerical(CString str, int offset) //convert a CString to its ASCII values, offseting each value
    {
        int k;
        int[] ASCIIarray = new int[str.getArray().length]; //creating an array to return
        for (int i = 0; i < str.getArray().length; i++)
        {
            k = Integer.parseInt(str.getArray()[i]); //had to ask AI about converting a string to an int, because I forgot how to
           k-=offset; //after converting each character in str to ASCII, offset each value by the parameter offset
           ASCIIarray[i] = k; //fill up ASCIIarray, the returning array, with the values of k, starting with 0
        }
        return ASCIIarray;
    }

    public static int maxMirror(CString str) //returns the maximum length of a pair of two reversed substrings of CString str
    {
        int MaxLength = 0;
        String string = str.toString();
        String substring1;
        String substring2;
        String substring3;
        for (int i = 0; i < str.getArray().length; i++)
        {
            for (int n = i; n < str.getArray().length-i; n++) //for two indexes within string
            {
                substring1 = string.substring(i, n); //take a substring out
                substring2 = reverse(substring1); //reverse it using the method below
                for (int a = string.length()-1; a > n; a--) //for another two indexes, going backwards now
                {
                    for (int b = string.length()-1-a; b > n+a; b--)
                    {
                        substring3 = string.substring(b,a); //take another substring
                        if (substring2.equals(substring3)&&substring2.length() == substring3.length()) //if the two substrings are equal in length and the one from the end is equal to the reverse of the one from the beginning
                        {
                            if (substring2.length() > MaxLength) //if the length of the substrings (individually) is greater than the current MaximumLength
                            {
                                MaxLength = substring2.length(); //that length is now the Maximum Length
                            }
                        }
                    }

                }
            }
        }
        return MaxLength; 
    }
    public static String reverse(String s) //reverses a string, helper method
    {
        String rev = "";
        
        for (int i = 0; i < s.length()-1; i++)
        {
            s.substring(s.length()-2-i, s.length()-1-i) = rev.substring(i, i+1); 
        }
        rev.substring(s.length()-1) = rev.substring(0, 1);
        return rev;

    }

    public static int[] memeifyArray(int[] nums)
    {
        for (int i = 0; i < nums.length; i++) //for every value in nums
        {
            if (nums[i] == 6) //if that value is 6
            {
                for (int j = 0; j < nums.length; j++) //search the array for a 7
                {
                    if (nums[j] == 7)
                    {
                        int temp = nums[i+1]; //place the seven after the 6
                        nums[i+1] = nums[j];
                        if (j > i) //if 7 is after 6 in the array
                        {
                            for (int k = i+1; k < j+1; k++) //shift elements to the right to fill in the empty space
                        {
                            nums[k+1] = nums[k]; //1 6 78    7 6 8 9 --> 
                        }
                        nums[i+2] = temp; //set the value after 7 to the prior value of the index that 7 is now at
                        }
                        if (j < i) //if 7 is before 6 in the array
                        {
                           int temp2 = nums[nums.length-1]; 
                           for (int h = 1; h <= j+1; h++) //loop all elements in nums from 1 to j+1 inclusive
                           {
                             nums[h] = nums[h-1]; //shift elements to the left to fill in the empty space
                           } //9    8 6 7 9 8
                           nums[0] = temp2; //set the first value of num to the saved last value of num
                        }

                    }
                }
            }
        }
        return nums; //return the 67-ed array
    }

   

    public static boolean nestedSequence(CString outer, CString inner) //discerns whether inner is contained within outer
    {
        boolean result = false; //return value default
        inner.sortAscending(); //sort inner and outer in ascending order (from CString class)
        outer.sortAscending();
        int[] arrInner = toNumerical(inner, 0); //set corresponding int type arrays for both CStrings
        int[] arrOuter = toNumerical(outer, 0); 
        
        for(int i = 0; i < arrInner.length; i++) //for every value in arrInner
        {
            for (int j : arrOuter) //for every value in arrOuter
            {
                if (arrInner[i] == j) //if that value in arrInner is somewhere in arrOuter
                {
                    result = true;      //set result equal to true     
                }
                else
                result = false; //otherwise the result must be false
            }
        }
        return result;

    }

    public static String decrypt(CString str) //to decrypt each CString
    {
        int count = 0;
        int[] encrypt = toNumerical(str, 0); //numerical ASCII array of str
        for (int i = 0; i < str.getArray().length-1; i++) //for every value in encrypt
        {
            if(encrypt[i] == encrypt[i+1]) //if a clump (2+ of the same value) exists
            {
                for (int j = i+1; j < encrypt.length-1; j++) //see how far it extends
                {
                    if (encrypt[j] != encrypt[j+1])
                    {
                        j = encrypt.length-1;
                        i = j+1; //move on to the numbers after the clump
                    }
                }
                count++; //count the number of clumps
            }
        }
        int[] newArray = toNumerical(str, -count); //create an offsetted ASCII array of str, offseting by the number of clumps
        int[] reverse = new int[newArray.length]; 
        for (int i = 0; i < newArray.length; i++)
        {
            reverse[newArray.length-1-i] = newArray[i]; //create a reversed version of the offsetted ASCII array of str
        }
        String end = ""; 
        for (int h = 0; h < reverse.length; h++)
        {
            end+=(char)(reverse[h]); //create a string representation of the ASCII; used ai to figure out how to convert from int to string 
        }
        return end; //return the string representation


    }


}
