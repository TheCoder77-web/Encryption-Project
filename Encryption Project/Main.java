// Leo and Ruien's Projecg

class Main {
  public static void main(String[] args) {
    (new Main()).init();
  }
  void print(Object o){ System.out.println(o);}
  void printt(Object o){ System.out.print(o);}

  void init(){
    //  Vowel characters
    char[] sub = new char[5];
    sub[0] = 'a';
    sub[1] = 'e';
    sub[2] = 'i';
    sub[3] = 'o';
    sub[4] = 'u';

    //  Unicode characters for Wingdings
    char[] sub2 = new char[5];
    sub2[0] = '\u264B';  // Cancer
    sub2[1] = '\u264F';  // Scorpio
    sub2[2] = '\u2653';  // Pisces
    sub2[3] = '\u25A1';  // White Square
    sub2[4] = '\u25C6';  // Diamond
    
    // Original Text
    String file = Input.readFile("Original.txt");
    // Encode level 1 (Wingdings)
    String encodedMsg1 = wingdings(file,sub,sub2);
    Input.writeFile("Encode1.txt", encodedMsg1);
    // // Encode level 2 (Atbash)
    String encodedMsg2 = atbash(encodedMsg1);
    Input.writeFile("Encode2.txt", encodedMsg2);
    // // Encode level 3 (Binary)
    String encodedMsg3 = encode(encodedMsg2);
    Input.writeFile("Encode3.txt", encodedMsg3);

    
    // Decoding the ciphertext: 
    String file2 = Input.readFile("Encode3.txt");
    // Decode level 1  (Binary)
    String decodedMsg1 = decode(file2);
    Input.writeFile("Decode1.txt", decodedMsg1);
    // Decode level 2 (Atbash)
    String decodedMsg2 = atbash(decodedMsg1);
    Input.writeFile("Decode2.txt", decodedMsg2);
    // Decode level 3 (Wingdings)
    String decodedMsg3 = wingdings(decodedMsg2, sub2, sub);
    Input.writeFile("Decode3.txt", decodedMsg3);
  }
 
  
  // Wingdings encoding
  String wingdings(String s, char[] sub, char[] sub2){
    String build = "";
    char ch ='\0';
    int index=0;
  
    for(int x=0; x<=s.length()-1; x++){
      ch = s.charAt(x);
      index = indexOf(ch,sub);
      if(index != -1){
        build += sub2[index];
      }
      else{
        build += ch;
      }
    }
    return build;
  }
  
  
  
  // Atbash encoding
  String atbash(String txt){
    String build = "";
  
    // Substitution stuff
    for(int x = 0; x <= txt.length() - 1; x++){
      char ch = txt.charAt(x);
      if(ch >= 'A' && ch <= 'Z'){
        ch = (char)('A' + ('Z' - ch));
      }else if(ch >= 'a' && ch <= 'z'){ 
        ch = (char)('a' + ('z' - ch));
      } 
      build += ch;
    } 
    return build;
  }

    
    
    // Binary encoding
    String encode(String txt){
      String build = "";

      for(int x = 0; x<=txt.length()-1; x++){
        char ch = txt.charAt(x);
        int ascii = (int)ch; 
    
        // Setting up a second build called "binary" and converting to binary process
        String binary = "";
        int Ascii = ascii; 
    
        // Converting stuff
        for(int i = 0; i < 16; i++){
          //  Using Modulus to convert to Binary
          if((Ascii % 2) == 1){
            binary = "1" + binary;
          }else{
            binary = "0" + binary;
          }
          Ascii = Ascii/2;
        }
        build += binary + " ";
      } 
      // Binary needs to remove trailing (Credits to various Coding Websites)
      return build.trim();
    }

    
    //  The harder part which is decoding binary :( 

    // Binary decoding since it needs its own function sadly 
    String decode(String txt){
      String build = "";
      // Splits the binary code (Credits to various coding websites)
      String[] binaryChars = txt.split(" ");
  
      for(int x = 0; x <= binaryChars.length - 1; x++){
        String binary = binaryChars[x];
        // Converting it back to a integer
        int ascii = 0;
        int num = 1; 
    
        // Changing it back to its original style process
        for(int i = binary.length()-1; i>=0; i--){
          char bi = binary.charAt(i);
        
          // Reverse calculation part 
          if(bi == '1'){
            ascii += num;
          }
          num *= 2;
        }
    
      // Converting it back to character
      char ch = (char)ascii;
      build += ch;
      }
      return build;
    }



  // identifying index of char within array
  int indexOf(char ch, char[] arry){
    for(int x=0; x<=arry.length-1; x++){
      if(arry[x] == ch){
        return x;
      }
    }
    return -1;
  }

  // random integer generator
  int randInt(int lower, int upper){
    int range = upper - lower + 1;
    return (int)(Math.random()*range) + lower;
  }

}