#  This grammar file is based on Daniel Howe's Haiku grammar
#  Which is based on a grammar by G.B. Kaminaga
# line-breaks are noted by '%' sign


{
<start>
<5-line> % <7-line> % <5-line>
}

{
<5-line>
 <1> <4>  |  <1> <3> <1>  |  <1> <1> <3>  |  <1> <2> <2>  |  <1> <2> <1> <1>  |  <1> <1> <2> <1>  |  <1> <1> <1> <2>  |  <1> <1> <1> <1> <1>  |  <2> <3>  |  <2> <2> <1>  |  <2> <1> <2>  |  <2> <1> <1> <1>  |  <3> <2>  |  <3> <1> <1>  |  <4> <1>  |  <5>
}

{
<7-line>
<1> <1> <5-line>  |  <2> <5-line>  |  <5-line> <1> <1>  |  <5-line> <2> 
}


{
<1>
a | ache | act | age | all | an | and | arch | are | as | asked | at | back | be | been | bends | bit | bridge | but | came | child | class | club | clubs | come | crowds | cut | day | dear | didn | don | down | dr | eight | feel | few | five | flags | folks | for | from | front | fun | get | got | had | have | he | hear | hearts | hold | home | hope | i | if | in | is | it | just | kick | killed | king | know | knows | like | lives | long | lose | lost | lot | makes | marched | most | must | my | not | of | off | old | on | one | or | out | passed | piped | place | proud | pull | quote | red | rights | s | said | sea | short | since | so | speech | staff | straight | struck | t | tell | that | the | their | them | there | these | they | think | this | those | through | to | try | up | us | ve | was | way | we | weeks | were | what | when | who | world | year | years | you | young | 
}

{
<2>
about | across | affairs | after | announced | balloons | because | before | billy | bobby | bono | campus | cheated | cheering | city | cleveland | country | daughter | describe | doing | drawing | even | famous | fashion | fired | forward | franklin | gathered | going | having | horses | justice | later | little | looking | maila | many | mayor | moral | morning | music | only | parent | parents | parted | people | rallies | random | raucous | riots | robert | running | sasha | stories | summer | taking | thousands | today | toward | tragic | upon | venues | voting | whether | wounded | 
}

{
<3>
aretha | audience | capitol | deliver | delivered | energy | enormous | imagine | kennedy | perspective | president | somebody | stemwinder | suddenly | together | typically | uncertain | universe | violence | visiting | 
}

{
<4>
appropriate | community | everybody | february | generating | society | usually | 
}

{
<5>
assassinated | extraordinary | participated | particularly | 
}
