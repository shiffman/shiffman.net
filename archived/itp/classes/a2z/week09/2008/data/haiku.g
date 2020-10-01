# based on a grammar by G.B. Kaminaga

# line-breaks are noted by '%' sign

{
  <start> 
  <5-line> % <7-line> % <5-line>  
}

{
  <5-line> 
  <1> <4>  |
  <1> <3> <1>  |
  <1> <1> <3>  |
  <1> <2> <2>  |
  <1> <2> <1> <1>  |
  <1> <1> <2> <1>  |
  <1> <1> <1> <2>  |
  <1> <1> <1> <1> <1>  |
  <2> <3>  |
  <2> <2> <1>  |
  <2> <1> <2>  |
  <2> <1> <1> <1>  |
  <3> <2>  |
  <3> <1> <1>  |
  <4> <1>  |
  <5>  |
}

{
  <7-line> 
  <1> <1> <5-line>  |
  <2> <5-line>  |
  <5-line> <1> <1>  |
  <5-line> <2>  |
}

{
  <1> 
  red  |  white  |  black  |  sky  |  dawns  |  breaks  |  falls  |  leaf  |
  rain  |  pool  |  my  |  your  |  sun  |  clouds  |  blue  |  green  |
  night  |  day  |  dawn  |  dusk  |  birds  |  fly  |  grass  |  tree  |
  branch  |  through  |  hell  |  zen  |  smile  |  gray  |  wave  |
  sea  |  through  |  sound  |  mind  |  smoke  |  cranes  | fish |
}

{
  <2> 
  drifting  |  purple  |
  mountains  |  skyline  |
  city  |  faces  | toward |
  empty  |  buddhist  |
  temple  |  japan  | under |
  ocean  |  thinking  |
  zooming  |  rushing  |
  over  |  rice field  |
  rising  |  falling  |
  sparkling  | snowflake
}

{
  <3>   
  sunrises  |  pheasant farms  |
  people farms  | samurai |
  juniper  |  fishing boats  |
  far away  |  kimonos  |  evenings |
  peasant rain | sad snow fall |
  
}

{
  <4> 
  aluminum  |  yakitori  |
  the east village |
  west of the sun |  
  chrysanthemums  |
  cherry blossoms  |
}

{
  <5> 
  resolutional  |  non-elemental  |
  rolling foothills rise  |
  toward mountains higher  |
  out over this country |
  in the springtime again |
}



