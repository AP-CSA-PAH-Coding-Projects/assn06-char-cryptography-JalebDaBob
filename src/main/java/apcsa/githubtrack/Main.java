package apcsa.githubtrack;

public class Main
{
    public CString[] rotate(CString[] arr, int d) //rotating the scrambled words d positions to the left
    {
        int x = 0; 
        CString first = arr[0]; //save the first CString in the array
        for (int i = 0; i < arr.length; i++)
        {
            if (i+d >= arr.length) //in case the number of positions to shift are greater than the array's actual length. 
            {
                x = i+d-arr.length;
                arr[i] = arr[x];
            }
            arr[i] = arr[i+d]; //loop through arr, and shift elements to the left
        }
        arr[arr.length-1] = first; //complete the shift by moving the first element to the end
        return arr;
    }

public static void main(String[] args)
{
    String message = "ydob sesaeler slacimehcoruen ekil enirhpenipe dna ,enilohclyteca hcihw esaercni ttfousfmb dna sucof dna etaerc na tnemnorivne evicudnoc ot ?gninrael gnicarbmE noitartsurf ,nac ,erofereht ecnahne ruoy niarb yticitsalp dna etatilicaf sfqffe !gnidnatsrednu rehtaR naht gniklaw yawa morf sksat taht ecudni ,noitartsurf gnitsisrep hguorht tfhofmmbid txpmmb uoy ot mmjse sfqffe otni eht gninrael -ttfdpsq gnicnahne ruoy laitnetop rof htworg dna .gninrael mmfX !enod uoY dluohs eb yrev duorp fo flesruoy rof gnitsisrep hguorht siht gnidoc fhofmmbid nevE fi ti saw ,drah noitartsurf dna eruliaf yalp YEK selor ni .gninrael diD uoy wonk taht gnikam tspssf slangis ot ruoy suovren metsys taht gnihtemos t'nsi ,gnikrow hcihw sdael ot segnahc ni ruoy laruen ?yrtiucric sihT si mbjuofttf rof ,yticitsalporuen ro eht s'niarb ytiliba ot tpada dna nrael morf .secneirepxe m'I erus ev'uoy decaf noitartsurf elihw /hojhhvcfe tuB did uoy wonk taht ni taht ,noitartsurf ruoy";
    CString[] array = new CString[message.length()];
    for (int i = 0;  i < message.length(); i++)
    {
        int j = message.indexOf(" "); //find every whitespace
        CString word = new CString(message.substring(0, j)); //make each word a CString
        array[i] = word; //store that CString into an array, filling up the array starting at index 0
        message = message.substring(j+1); //remove that word, and continue.
    }
    for (int i = 0; i < array.length; i++) //for every CString in array
    {
        array[i] = array[i].decrypt(array[i]); //decrypt that array, using the CStringUtil method
    }

    
    int Max = 0;

    for (CString c : array) //looping through the message array
    {
        int[] num = c.toNumerical(c, 0);
        for (int i : num) //for every character in the current CString's numerical form
        {
            if (i > Max) //finding the largest ASCII value in the numerical array of the current CString of the whole array
            {
                Max = i;
            }
        }
    }
    Max-=60; //editing the maximum value to input into the rotation method

    rotate(array, Max); //rotate the array according the calculated "d" value of the maximum ASCII value in array minus 60

    for (CString c : array) //return each decrypted CString to receive the whole message
    {
        System.out.print(c);
    }



}
}
    // Implement your main application logic here
