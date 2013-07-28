# Worst Grammar Ever
# For use with RiTa: http://www.rednoise.org/rita/documentation/rigrammar_class_rigrammar.htm
# s -> a b
# a -> 'the'
# b -> c 'cat' 
# c -> 'happy' | 'sad'

{
<start>
<a> <b>
}

{
<a>
the
}

{
<b>
<c> cat
}

{
<c>
happy | sad
}