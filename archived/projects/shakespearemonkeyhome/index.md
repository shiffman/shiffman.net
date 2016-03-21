---
title: ShakespeareMonkey@Home
author: Daniel
layout: page
dsq_thread_id:
  - 249553051
pvc_views:
  - 2369
---
<p><b>ShakespeareMonkey@Home</b><br />
A somewhat odd attempt to answer the question:</p>
<p><img src ="http://shiffman.net/images/question.gif"/></p>
<p>(Please note no monkeys were harmed during the course of this experiment.)</p>
<p>ShakespeareMonkey@Home is a distributed application that simulates a population of evolving monkeys typing lines from The Complete Works of William Shakespeare (Each client represents one monkey family typing). The family connects to the &#8220;Zoo&#8221; (a mySQL database containing Shakespeare&#8217;s complete works), selects a play to work on, and receives a line from that play to type.  Once the line is completed, the family reports its work to the zoo and waits to receive a new line.</p>
<p><b>CURRENT MONKEY STATUS</b>:  Unfortunately the server that housed the database is no longer operational.  As soon as I can get the server application back up and running in a new home, the client will be available again.</p>
<p><b>Shakespeare Monkey Probability</b></p>
<p>For our purposes, we won&#8217;t use an actual monkey or a typewriter.  Instead, we will employ a digital monkey simulation &#8212; for now, let&#8217;s call our monkey George.</p>
<p>George types at a reduced keyboard, containing only 32 characters:</p>
<blockquote><p>
26 letters<br />
1 space bar<br />
5 punctuation marks
</p></blockquote>
<p>On George&#8217;s keyboard, the space bar is slightly larger than the other keys and George&#8217;s fingers tend to hit it more often. The probability of George striking any given key is as follows:</p>
<blockquote>
<table border = 1>
<tr>
<td width = 250 class =" content">Each Letter and Punctuation Mark</td>
<td class =" content">1 in 34</td>
</tr>
<tr>
<td class =" content">The space bar</td>
<td class =" content">3 in 34</td>
</tr>
</table>
</blockquote>
<p>Consider the phrase:</p>
<blockquote><p>
To be, or not to be: that is the question:
</p></blockquote>
<p>The phrase is 42 characters long &#8212; 9 space bars, and 33 other characters.  If George starts typing, the chance he&#8217;ll get the first character right is 1 in 34.  Since the probability he&#8217;ll get the second character right is also 1 in 34, he has a 1 in 34*34 chance of getting landing on both the first two characters.  It follows that the probablility that George will type the full Shakespearan stanza is:</p>
<blockquote><p>
((1 / 34) ^ 33) * ((3 / 34) ^ 9)<br />
or ~ 1 in 1.0666531575100571968353546589146 * 10^60
</p></blockquote>
<p>That exact # is a:</p>
<blockquote><p>
1 in 1,066,653,157,510,057,196,835,354,658,<br />
914,553,991,296,211,872,584,111,802,259,963
</p></blockquote>
<p>chance of George getting it right.</p>
<p>Our digital monkey, however, is a pretty fast typer &#8212; George can do about 20 lines per second.  How long would it take, therefore, for George to have a 99% probability of eventually getting it right?  To compute this number, we&#8217;ll first need to consider how many lines George would have to type to have a 1% probability of <i>getting it wrong</i> every single time.</p>
<p>Assume N = that long # listed above (the probability of getting it right on one attempt).</p>
<p>The probablity of George getting it wrong on one try is therefore:</p>
<blockquote><p>(1 &#8211; 1/N)</p></blockquote>
<p>We want George to have a 1 out of 100 chance of getting it wrong over <strong>X</strong> # of tries, therefore:</p>
<blockquote><p>
(1 &#8211; 1/N) ^ X = 1/100 which is equivalent to:<br />
((N-1)/N) ^ X = 1/100
</p></blockquote>
<p>Solving for x, we take the natural log of each side:</p>
<blockquote><p>
ln((N-1)/N) ^ X) = ln(1/100)<br />
X * ln((N-1)/N) = ln(1/100)<br />
X = ln(100) / (ln(N/(N-1))
</p></blockquote>
<p>As N goes to infinity, ln(N/(N-1)) = ~1/N, therefore:</p>
<blockquote><p>
X = ~ N * ln(100)
</p></blockquote>
<p>Since George types 20 lines per second, we can say that he&#8217;ll type 630,720,000 lines per year.  The number of years it will take him is:</p>
<blockquote><p>
(N / 630,720,000) * ln(100) = 7.8 * 10^51 years
</p></blockquote>
<p>Ok, ok, so that&#8217;s a really long time.  But what if, say, we have a hundred trillion monkeys all typing at once?  How long would it take to have a 99% probability of one of the monkeys getting it right?  A few calculations will lead us to the conclusion that this will take about</p>
<blockquote><p>
7.8 * 10^37 years
</p></blockquote>
<p>Gulp.</p>
<p><b>The Collaborative Monkey</b></p>
<p>Consider this, however.  What if all the monkeys are connected together via a massive network, and 1 monkey tracked all of the monkey&#8217;s activity in a database?  And instead of typing randomly, each monkey would type in sequence:</p>
<blockquote><p>
1. aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa<br />
2. aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab<br />
3. aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaac
</p></blockquote>
<p>Each character represents a number from 1 to 32 (i.e. a 5-bit number).  If each character can be represented by a 5-bit number, than the entire string can be represented by a 210-bit number.  So consider if the total (2^210) was divided into 2^194 &#8220;blocks&#8221; of numbers.  Each monkey connects to ServerMonkey and retrieves a random 194-bit number outlining which block to check.  The monkey is then required to count through all the possibilities inside their block &#8212; starting at (2^16)*block# and finishing at ((2^16)*(block#+1))-1 (note block #&#8217;s start at 0).  Each monkey would be able to complete a block in ~55 minutes. How long would it take for a 99% probability of &#8220;To be or not to be. . .&#8221; with the 100 trillion collaborative monkeys going all at once (never typing the same line twice)? Sadly, the answer is still a massively large number &#8212; <strong>3.9 * 10^35 years</strong>.  I should point out, however, that we have saved ourselves about 7.761 * 10^37 years.  Not so bad, eh?</p>
<p><b>The Genetically Enhanced Monkey</b></p>
<p>Even though millions of monkeys working together on a network has improved our result on a massive scale, we still haven&#8217;t solved the problem of turning a monkey into shakespeare in an amount of time that any human could experience in a given lifetime.</p>
<p>What if we bred digital monkeys with DNA signatures &#8212; and these DNA signatures determine the pattern by which the monkeys type 42 characters?  A monkey&#8217;s &#8220;fitness&#8221; would be the number of characters that match &#8220;to be or not to be. . .&#8221; in his or her DNA.  Let&#8217;s start with a population of 100 monkeys and give the high fitness monkeys a proportionately higher chance of being selected for mating.  At birth, each monkey child&#8217;s DNA is a combination of two selected parent monkeys.  (In addition, each individual character in the child&#8217;s DNA string also has a small probability of &#8220;mutation&#8221; &#8212; i.e. of becoming a random character, rather than one from the parent.)</p>
<p>After running the simulation only once, the genetically enhanced monkeys were able to type the line of Shakespeare in 30 seconds, after only 198 generations of monkeys.  In a real-world setting, if a new generation of live monkeys could be birthed every 10 years, we&#8217;d only have to wait 1,980 years for monkey + typewriter = shakespeare.</p>
<p><b>The Genome</b> </p>
<p>We have established that the monkey&#8217;s DNA must determine that individual monkey&#8217;s pattern of typing.   Let&#8217;s assume we want our monkeys to type &#8220;To be or not to be: That is the question:&#8221;  There are 32 characters on the keyboard, and 42 characters in the desired string.  How can we express the monkey&#8217;s DNA as binary information?</p>
<p>Each key on the monkey&#8217;s typewriter corresponds to a 32-bit number.</p>
<blockquote><p>
KEY # 1 = &#8216;a&#8217; or &#8217;00000&#8242;<br />
KEY # 2 = &#8216;b&#8217; or &#8217;00001&#8242;<br />
KEY # 3 = &#8216;c&#8217; or &#8217;00010&#8242;<br />
etc. etc. etc. up until<br />
KEY # 32 = SPACE or &#8217;11111&#8242;
</p></blockquote>
<p>Let&#8217;s take the genome:</p>
<blockquote><p>
&#8217;0101100111101010100101010101011110000101&#8242;
</p></blockquote>
<p>We can divide it in to 5-bit segments:</p>
<blockquote><p>
&#8217;01011 00111 10101 01001 01010 10101 11100 00101&#8242;
</p></blockquote>
<p>This translates to decimal:</p>
<blockquote><p>
&#8217;11,7,21,9,10,21,28,5&#8242;
</p></blockquote>
<p>Which translates to a typing sequence of key #&#8217;s:</p>
<blockquote><p>
12,8,22,10,11,22,29,6
</p></blockquote>
<p>And which translates to the phrase:</p>
<blockquote><p>
lguiju;f
</p></blockquote>
<p>So now, we have a monkey somewhat like Jack Torrence in <i>The Shining</i>, only instead of &#8220;All work and no play make Jack a dull boy,&#8221; we have a monkey that types pages and pages of &#8220;lguiju;flguiju;flguiju;flguiju;flguiju;f. . . &#8221;</p>
<p>For us to get &#8220;To be or not to be: That is the question:&#8221; out of the monkey, we&#8217;ll need a longer genome &#8212; a 210-bit character one.</p>
<p>For example:</p>
<blockquote><p>
&#8220;1000000111101110000000101000000110000111010110010001000000<br />
000101011000010110100000011110110101111100101101000010000001<br />
01010111001001110010011000111011001010111110101010011000111011<br />
101001110011111001100000110001&#8243;
</p></blockquote>
<p>or:</p>
<blockquote><p>
16, 7, 23, 0, 5, 0, 12, 7, 11, 4, 8, 0, 10, 24, 11, 8, 3, 27, 11, 28, 22, 16, 16, 5, 11, 18, 14, 9, 17, 27, 5, 15, 21, 9, 17, 27, 20, 28, 31, 6, 1, 17
</p></blockquote>
<p>or:</p>
<blockquote><p>
&#8220;qhxafamhleiakylid:l;wqqflsojr:fpvjr:u; gbr&#8221;
</p></blockquote>
<p>Suppose we have a group of 5 monkeys, with the following DNA sequences (expressed as a character string for simplification).</p>
<blockquote><p>
1. &#8220;,:drxkaitxsdp: f?ynjasqosupchujk,, rizhg:r&#8221;<br />
2. &#8220;cklavicmemt :bbkyyn fjgd?y?k?dl&#8217;goyponko&#8217;a&#8221;<br />
3. &#8220;lt;wv  hmstx,cxy &#8216;,mo;vwwpbqkuibuhq;f&#8217;q'ow&#8221;<br />
4. &#8220;zqayapqfiszwrh ?isye: ghg:mllowx&#8217;zc&#8217;uyadly&#8221;<br />
5. &#8220;ielcm,sc dnzm,ngeuviu:lu;zo ojxwpizvk;adqe&#8221;
</p></blockquote>
<p><b>Reproduction</b></p>
<p>Each monkey&#8217;s fitness is the # of characters in their DNA that match &#8220;To be, or not to be: That is the question:&#8221;</p>
<blockquote><p>
1. Fitness = 0<br />
2. Fitness = 1<br />
3. Fitness = 2<br />
4. Fitness = 0<br />
5. Fitness = 2</p>
<p>Total Fitness = 5</p>
<p>1. % of Total Fitness = 0%<br />
2. % of Total Fitness = 20%<br />
3. % of Total Fitness = 40%<br />
4. % of Total Fitness = 0%<br />
5. % of Total Fitness = 40%
</p></blockquote>
<p>Next, we will select parent monkeys for breeding.  Each monkey&#8217;s chance at being selected is equal to their % of Total Fitness. In this case, it&#8217;s as if we spun a roulette wheel with these possible outcomes: 2,3,3,5,5 (since Monkeys 1 and 3 scored a zero,<br />
they are not eligible for reproduction). Let&#8217;s assume we randomly select monkeys #3 and #5 to be our first parents. </p>
<p><b>Crossover</b></p>
<p>Next we pick a random point in the chromosone string for crossover.  For a true genetic algorithm, we would revisit our 210-bit representation of the genome, but for our purposes, we&#8217;ll just pick a # between 2 and 41 and deal with the character string. Let&#8217;s say we pick 18.  For our new monkey, we take the first 18 characters of Monkey #3 DNA:</p>
<blockquote><p>
lt;wv  hmstx,cxy
</p></blockquote>
<p>and the last 24 characters of Monkey #5 DNA:</p>
<blockquote><p>
viu:lu;zo ojxwpizvk;adqe
</p></blockquote>
<p>Our new DNA is:</p>
<blockquote><p>
&#8220;lt;wv  hmstx,cxy &#8216;viu:lu;zo ojxwpizvk;adqe&#8221;
</p></blockquote>
<p><b>Mutation</b></p>
<p>We&#8217;re not done, however.  Let&#8217;s assume there is a 2% chance of genetic mutation on any given chromosone in the newly born monkey&#8217;s DNA and chromosone #8 changes to an &#8220;e&#8221;.  Our final DNA:</p>
<blockquote><p>
&#8220;lt;wv  emstx,cxy &#8216;viu:lu;zo ojxwpizvk;adqe&#8221;
</p></blockquote>
<p>Well, we haven&#8217;t gotten &#8220;to be or not to be. . .&#8221; yet, but we do have a newly born monkey with a fitness of 2.  If we increase the monkey population size from 5 to 100, and iterate the process over several hundred generations, we will most likely find a monkey with a DNA string of &#8220;to be, or not to be: that is the question:&#8221;.</p>
<p>Now, what we need is a zoo of monkey families, all working on different parts of Hamlet at the same time &#8212; a distributed network of evolutionary typing monkeys. . .</p>
