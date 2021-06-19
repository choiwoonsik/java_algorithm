package Silver이하문제_모음;

import java.util.ArrayList;
import java.util.Iterator;

class Solution {
    public int solution(String[] words) {
        Word word;
        int answer = 0;
        ArrayList<Word> list = new ArrayList<>();

        for (int i = 0; i < words.length; i++)
        {
            word = new Word(words[i], 0);
            list.add(word);
        }

        while (!list.isEmpty())
        {
            Word now = list.get(0);
            list.remove(0);
            Iterator<Word> iter = list.iterator();
            while (iter.hasNext())
            {
                Word next = iter.next();
                next.start = now.start;
                while (now.start < now.word.length() && next.start < next.word.length()) {
                    if (now.word.charAt(now.start) == next.word.charAt(next.start))
                    {
                        now.start++;
                        next.start++;
                    }
                }
                if (now.start < now.word.length())
                    now.start++;
                if (next.start < next.word.length())
                    next.start++;
                iter.remove();
            }
        }

        return answer;
    }
}

class Word
{
    String  word;
    int     start;
    Word (String word, int start)
    {
        this.word = word;
        this.start = start;
    }
}