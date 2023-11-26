import Compression.Lz77;
import Compression.Lzw;
import File.FileController;

public class Main {
    public static void main(String[] args) throws Exception {
        Lzw lzw = new Lzw();
        FileController file = new FileController();
        file.write(lzw.deCompress(file.read("input.txt")));

    }
//lzw
// ABAABABBAABAABAAAABABBBBBBBB
//65,66,65,128,128,129,131,134,130,129,66,138,139,138




//lz77
/*
    ABBBACBABCACAACBABCA
        <0, 0, A>
        <0, 0, B>
        <1, 2, A>
        <0, 0, C>
        <3, 2, B>
        <4, 1, A>
        <2, 2, A>
        <9, 5, A>


    ABAABABABABABABABABABA
        <0, 0, A>
        <0, 0, B>
        <2, 1, A>
        <3, 2, B>
        <2, 14, A>
        <0,0,A> <0,0,B> <2,1,A> <3,2,B> <2,14,A>

    ABCABCABCABC
        <0, 0, A>
        <0, 0, B>
        <0, 0, C>
        <3, 8, C>
        <0,0,A> <0,0,B> <0,0,C> <3,8,C>

    aacaacabcabaaac
        <0, 0, a>
        <1, 1, c>
        <3, 4, b>
        <3, 3, a>
        <1, 2, c>

    ABAABABAABBBBBBBBBBBBA
        <0, 0, A>
        <0, 0, B>
        <2, 1, A>
        <3, 2, B>
        <5, 3, B>
        <1, 10, A>
 */


}