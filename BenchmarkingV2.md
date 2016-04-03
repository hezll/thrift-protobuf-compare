### Benchmarks (2011-01-25) ###

**WARNING**: Benchmarks can be misleading.
  * These tests use a specific data value (DataStructuresV2).  A different data value will yield different results.
  * The tools have different sets of features (BeyondNumbers).  Some of these features make things safer or easier, but come with a performance cost.
  * Different hardware and software environments will yield different results.
  * We don't take memory usage into account.

In short, before you make a decision on which tool to use, make sure you try it out in an environment you care about.  To start, download the benchmark code and run it on _your_ hardware with data values _you_ care about.

## Setup ##

Hardware: Intel Core 2 Quad 2.66GHz

Software: Sun JRE 1.6.0\_22 (64-bit server VM), Ubuntu 10.04

JVM options: -Xmx16m -server

Data value being tested: DataStructuresV2.

Version of the benchmarking code: <a href='https://github.com/eishay/jvm-serializers/tree/0e353b5e90799b328f21664b730598dfd9618beb/tpc'>Git tree</a>

Methodology:
  * Before taking measurements, we warm things up by running the test several times.
  * For a test, measure the time taken to perform 2000 operations (serialization, deserialization, etc.).  Then divide the result by 2000.
  * Run each test 500 times and report the best result.
  * Look at the code for more details. [BenchmarkRunner.java](http://github.com/eishay/jvm-serializers/tree/1f16663c3acafd998f59b4c9e39020be1878bd60/tpc/src/serializers/BenchmarkRunner.java)

Tool Versions ([lib/](http://github.com/eishay/jvm-serializers/tree/1f16663c3acafd998f59b4c9e39020be1878bd60/tpc/lib)):
  * [protobuf](http://code.google.com/p/protobuf/) 2.3.0
  * [thrift](http://incubator.apache.org/thrift/) 0.4.0
  * [avro](http://hadoop.apache.org/avro/) 1.3.2
  * [kryo](http://code.google.com/p/kryo/) 1.03
  * [hessian](http://hessian.caucho.com/) 4.0.3
  * [activemq-protobuf](http://activemq.apache.org/) 1.1-SNAPSHOT
  * [scala](http://scala-lang.org/) 2.8.0-rc1
  * [sbinary](http://github.com/harrah/sbinary) 0.3.1-SNAPSHOT
  * [google-gson](http://code.google.com/p/google-gson/) 1.5
  * [jackson](http://jackson.codehaus.org/) 1.7.1
  * [javolution](http://javolution.org/) 5.2.6
  * [protostuff](http://code.google.com/p/protostuff/) 1.0.0.M7
  * [woodstox](http://woodstox.codehaus.org/) 4.0.7
  * [aalto](http://www.cowtowncoder.com/hatchery/aalto/index.html) 0.9.5
  * [fast-infoset](http://fi.dev.java.net/) 1.2.6
  * [xstream](http://xstream.codehaus.org/) 1.3.1

# Charts #

## Total Time ("total") ##

Create an object, serialize it to a byte array, then deserialize it back to an object.

<img src='http://chart.apis.google.com/chart?chtt=total&chf=c||lg||0||FFFFFF||1||76A4FB||0|bg||s||EFEFEF&chs=700x390&chd=t:3396,3477,3606,3714,3821,4418,4472,5173,5433,6344,6640,6817,7034,7094,7342,7481,7717,8002,8138,9170,9590,11892,12268,13005,19082,20038,22436,23467,26402,27639,31339,32480,41521,42950,44923,63267,75261,112497,140017,173274&chds=0,190602.35259999998&chxt=y&chxl=0:|json%2Fgoogle-gson|json%2Fprotobuf|scala%2Fjava-built-in|java-built-in|xml%2Fxstream%2Bc|bson%2Fmongodb|xml%2Fxstream%2Bc-fastinfo|xml%2Fxstream%2Bc-woodstox|xml%2Fxstream%2Bc-aalto|xml%2Fmanual-fastinfo|xml%2Fjavolution|bson%2Fjackson-databind|hessian|bson%2Fjackson-manual|xml%2Fjackson-databind%2Faalto|xml%2Fmanual-woodstox|avro-generic|avro|xml%2Fmanual-aalto|json%2Fjackson-databind|json%2Fprotostuff-runtime|smile%2Fjackson-databind|smile%2Fprotostuff-runtime|json%2Fprotostuff-manual|scala%2Fsbinary|json%2Fjackson-manual|thrift|protobuf%2Factivemq%2Balt|thrift-compact|protobuf|smile%2Fprotostuff-manual|smile%2Fjackson-manual|kryo|kryo-opt|protostuff-runtime|kryo-manual|protobuf%2Fprotostuff|protostuff|protostuff-manual|java-manual&chm=N *f*,000000,0,-1,10&lklk&chdlp=t&chco=660000|660033|660066|660099|6600CC|6600FF|663300|663333|663366|663399|6633CC|6633FF|666600|666633|666666&cht=bhg&chbh=5,0,4&nonsense=aaa.png' />

## Serialization Time ("ser") ##

Create an object, serialize it to a byte array.
  * Java's built-in serializer faithfully represents arbitrary object graphs, which hurts performance.  All the other serializers flatten the structure out to a tree.

<img src='http://chart.apis.google.com/chart?chtt=ser&chf=c||lg||0||FFFFFF||1||76A4FB||0|bg||s||EFEFEF&chs=700x390&chd=t:1430,1443,1511,1777,1860,1907,2185,2418,2544,2718,2914,3243,3562,3573,3728,3814,4127,4161,4195,4289,4472,4496,5004,5069,7613,8175,8922,9758,10444,11201,11664,11783,13413,13917,14879,15809,20343,21048,23598,97269&chds=0,106996.21075000001&chxt=y&chxl=0:|json%2Fgoogle-gson|json%2Fprotobuf|scala%2Fjava-built-in|xml%2Fxstream%2Bc-fastinfo|xml%2Fxstream%2Bc|xml%2Fmanual-fastinfo|xml%2Fxstream%2Bc-woodstox|java-built-in|bson%2Fjackson-databind|xml%2Fxstream%2Bc-aalto|hessian|xml%2Fjavolution|bson%2Fjackson-manual|bson%2Fmongodb|xml%2Fjackson-databind%2Faalto|xml%2Fmanual-woodstox|avro-generic|xml%2Fmanual-aalto|avro|thrift|protobuf%2Factivemq%2Balt|thrift-compact|protobuf|scala%2Fsbinary|json%2Fprotostuff-runtime|json%2Fjackson-databind|smile%2Fjackson-databind|smile%2Fprotostuff-runtime|json%2Fprotostuff-manual|smile%2Fprotostuff-manual|json%2Fjackson-manual|kryo|smile%2Fjackson-manual|kryo-opt|java-manual|protostuff-runtime|kryo-manual|protobuf%2Fprotostuff|protostuff|protostuff-manual&chm=N *f*,000000,0,-1,10&lklk&chdlp=t&chco=660000|660033|660066|660099|6600CC|6600FF|663300|663333|663366|663399|6633CC|6633FF|666600|666633|666666&cht=bhg&chbh=5,0,4&nonsense=aaa.png' />

## Deserialization Time ("deser+deep") ##

Often the most expensive operation.  To make a fair comparison, all fields of the deserialized instances are accessed - this forces lazy deserializers to really do their work. The raw data below shows additional measurements for deserialization.

<img src='http://chart.apis.google.com/chart?chtt=deser%2Bdeep&chf=c||lg||0||FFFFFF||1||76A4FB||0|bg||s||EFEFEF&chs=700x390&chd=t:1489,2044,2047,2163,2202,2286,2479,2558,2621,2622,2628,2745,3015,3353,3430,4440,4474,4564,4624,5355,5861,6888,7772,7936,11468,11862,12266,12677,14619,16460,17194,20816,22607,27603,36000,47458,61848,76005,91448,116419&chds=0,128061.0991&chxt=y&chxl=0:|json%2Fprotobuf|scala%2Fjava-built-in|json%2Fgoogle-gson|java-built-in|xml%2Fxstream%2Bc|bson%2Fmongodb|xml%2Fxstream%2Bc-woodstox|xml%2Fxstream%2Bc-fastinfo|xml%2Fxstream%2Bc-aalto|xml%2Fjavolution|xml%2Fmanual-fastinfo|bson%2Fjackson-databind|bson%2Fjackson-manual|hessian|xml%2Fjackson-databind%2Faalto|xml%2Fmanual-woodstox|avro-generic|avro|xml%2Fmanual-aalto|json%2Fjackson-databind|json%2Fprotostuff-runtime|json%2Fjackson-manual|smile%2Fjackson-databind|json%2Fprotostuff-manual|smile%2Fprotostuff-runtime|smile%2Fprotostuff-manual|scala%2Fsbinary|smile%2Fjackson-manual|protobuf%2Factivemq%2Balt|kryo|thrift|thrift-compact|protostuff-runtime|protobuf|kryo-opt|protobuf%2Fprotostuff|protostuff|protostuff-manual|kryo-manual|java-manual&chm=N *f*,000000,0,-1,10&lklk&chdlp=t&chco=660000|660033|660066|660099|6600CC|6600FF|663300|663333|663366|663399|6633CC|6633FF|666600|666633|666666&cht=bhg&chbh=5,0,4&nonsense=aaa.png' />

## Serialized Size ("size") ##

The size of the serialized data.  These numbers may vary depending on the exact data value being used.
  * Java's built-in serializer stores the full class name in serialized form.  So you don't need to know ahead of time what kind of object you're reading in.
  * The 'scala' test, which uses Java's built-in serialization, yields a larger serialized representation because it usually creates more Java classes under the hood.

<img src='http://chart.apis.google.com/chart?chtt=size&chf=c||lg||0||FFFFFF||1||76A4FB||0|bg||s||EFEFEF&chs=700x390&chd=t:219,219,221,221,233,239,239,239,239,239,240,241,255,255,325,339,341,345,349,364,377,449,468,469,486,487,488,495,495,501,503,504,519,525,525,653,653,712,889,1312&chds=0,1443.2&chxt=y&chxl=0:|scala%2Fjava-built-in|java-built-in|xml%2Fjackson-databind%2Faalto|xml%2Fmanual-woodstox|xml%2Fmanual-aalto|xml%2Fxstream%2Bc-woodstox|xml%2Fxstream%2Bc-aalto|bson%2Fjackson-databind|xml%2Fjavolution|json%2Fjackson-databind|hessian|bson%2Fjackson-manual|bson%2Fmongodb|json%2Fprotobuf|xml%2Fxstream%2Bc|json%2Fgoogle-gson|json%2Fprotostuff-runtime|json%2Fjackson-manual|json%2Fprotostuff-manual|xml%2Fmanual-fastinfo|smile%2Fjackson-databind|thrift|xml%2Fxstream%2Bc-fastinfo|smile%2Fjackson-manual|smile%2Fprotostuff-runtime|smile%2Fprotostuff-manual|java-manual|scala%2Fsbinary|protostuff-runtime|thrift-compact|protostuff-manual|protobuf%2Factivemq%2Balt|protostuff|protobuf|protobuf%2Fprotostuff|kryo|avro-generic|avro|kryo-manual|kryo-opt&chm=N *f*,000000,0,-1,10&lklk&chdlp=t&chco=660000|660033|660066|660099|6600CC|6600FF|663300|663333|663366|663399|6633CC|6633FF|666600|666633|666666&cht=bhg&chbh=5,0,4&nonsense=aaa.png' />

## Serialization Compressed Size ("size+dfl") ##

The size of the serialized data compressed with Java's built-in implementation of DEFLATE (zlib).

<img src='http://chart.apis.google.com/chart?chtt=size%2Bdfl&chf=c||lg||0||FFFFFF||1||76A4FB||0|bg||s||EFEFEF&chs=700x390&chd=t:132,133,133,135,147,147,147,148,149,149,149,150,150,151,197,233,233,240,243,244,244,253,253,259,260,263,264,270,273,273,278,278,284,293,297,304,304,313,517,700&chds=0,770.0000000000001&chxt=y&chxl=0:|scala%2Fjava-built-in|java-built-in|hessian|xml%2Fmanual-woodstox|xml%2Fmanual-aalto|xml%2Fjackson-databind%2Faalto|bson%2Fjackson-databind|xml%2Fmanual-fastinfo|bson%2Fjackson-manual|bson%2Fmongodb|xml%2Fxstream%2Bc-woodstox|xml%2Fxstream%2Bc-aalto|json%2Fjackson-databind|xml%2Fxstream%2Bc-fastinfo|xml%2Fjavolution|smile%2Fjackson-databind|json%2Fgoogle-gson|json%2Fjackson-manual|json%2Fprotobuf|smile%2Fjackson-manual|xml%2Fxstream%2Bc|json%2Fprotostuff-runtime|smile%2Fprotostuff-runtime|json%2Fprotostuff-manual|smile%2Fprotostuff-manual|thrift|protostuff-runtime|protostuff-manual|protostuff|protobuf%2Factivemq%2Balt|protobuf|protobuf%2Fprotostuff|thrift-compact|kryo|java-manual|scala%2Fsbinary|kryo-opt|avro-generic|avro|kryo-manual&chm=N *f*,000000,0,-1,10&lklk&chdlp=t&chco=660000|660033|660066|660099|6600CC|6600FF|663300|663333|663366|663399|6633CC|6633FF|666600|666633|666666&cht=bhg&chbh=5,0,4&nonsense=aaa.png' />

## Object Creation Time ("create") ##

Object creation is not so meaningful since it takes in average 100 nano to create an object.  However, the different tools vary in how "fancy" their objects are.  Some just create a plain Java class and let you access fields directly, while others have set/get methods, while others use the "builder" pattern.
  * Protobuf and Thrift use the "builder" pattern to create objects, which makes the operation more expensive.
  * Avro stores Strings in UTF8 form.  The time taken to convert from Java "String" values to UTF-8 is included under "create", "ser", "deser+shal", and "deser+deep", which isn't quite representative of real-world usage.  Real code that uses Avro might be able to keep strings in UTF-8 form, thus avoiding the need to convert back and forth (in which case the "ser+same" and "deser" results might be more accurate reflections of Avro's performance).

<img src='http://chart.apis.google.com/chart?chtt=create&chf=c||lg||0||FFFFFF||1||76A4FB||0|bg||s||EFEFEF&chs=700x390&chd=t:217,218,219,220,221,221,222,222,222,222,222,223,223,223,223,223,223,223,225,225,228,229,229,229,229,229,229,229,229,343,344,384,415,417,500,501,662,675,1789,2417&chds=0,2658.8452935000005&chxt=y&chxl=0:|avro-generic|avro|scala%2Fsbinary|scala%2Fjava-built-in|json%2Fprotobuf|protobuf|thrift-compact|thrift|protobuf%2Factivemq%2Balt|protobuf%2Fprotostuff|protostuff|smile%2Fprotostuff-manual|xml%2Fxstream%2Bc|xml%2Fxstream%2Bc-fastinfo|xml%2Fxstream%2Bc-aalto|xml%2Fmanual-fastinfo|bson%2Fjackson-databind|json%2Fjackson-manual|xml%2Fjavolution|xml%2Fxstream%2Bc-woodstox|kryo|kryo-opt|smile%2Fprotostuff-runtime|bson%2Fjackson-manual|json%2Fprotostuff-runtime|smile%2Fjackson-databind|xml%2Fmanual-woodstox|bson%2Fmongodb|xml%2Fjackson-databind%2Faalto|smile%2Fjackson-manual|xml%2Fmanual-aalto|protostuff-manual|protostuff-runtime|json%2Fprotostuff-manual|json%2Fgoogle-gson|json%2Fjackson-databind|kryo-manual|java-manual|hessian|java-built-in&chm=N *f*,000000,0,-1,10&lklk&chdlp=t&chco=660000|660033|660066|660099|6600CC|6600FF|663300|663333|663366|663399|6633CC|6633FF|666600|666633|666666&cht=bhg&chbh=5,0,4&nonsense=aaa.png' />

# Numbers #

Times are in nanoseconds, sizes are in bytes.
```
                                 create     ser   +same   deser   +shal   +deep   total   size  +dfl
java-built-in                       217   13413   12065   60842   61022   61848   75262    889   517
java-manual                         219    1907    1693    1298    1378    1489    3396    255   147
scala/java-built-in                 663   21049   18217   89160   90141   91449  112498   1312   700
scala/sbinary                       676    4128    3246    2871    3027    3354    7481    255   147
hessian                             219   11201   10327   11996   12144   12267   23468    501   313
kryo                                226    2545    2626    2483    2533    2628    5173    233   147
kryo-opt                            226    2185    2390    2138    2192    2287    4472    219   135
kryo-manual                         221    1777    1587    1838    1908    2044    3822    219   132
protobuf                            501    4161    2024    2181    2306    2479    6640    239   149
protobuf/activemq+alt               385    4289      10      17    1375    2745    7035    239   149
protostuff                          343    1444    1191    1975    2059    2163    3607    239   150
protostuff-manual                   223    1430    1208    1688    1905    2047    3477    239   150
protostuff-runtime                  222    1861    1658    2197    2405    2558    4419    241   151
protobuf/protostuff                 344    1512    1285    2054    2105    2202    3714    239   149
thrift                              416    4472    4080    2448    2509    2623    7095    349   197
thrift-compact                      417    4196    3676    2376    2457    2622    6817    240   148
avro                               1790    4497    2696    6018    6959    7772   12269    221   133
avro-generic                       2417    5070    2412    5518    6752    7936   13006    221   133
json/jackson-manual                 229    2718    2530    3978    4268    4624    7343    468   253
json/jackson-databind               221    3728    3470    5393    5514    5862    9590    503   270
json/protostuff-manual              222    3243    2970    3995    4236    4475    7718    449   233
json/protostuff-runtime             223    3815    3460    4905    4999    5356    9171    469   243
json/protobuf                       501   23598   22241  115652  116043  116419  140018    488   253
json/google-gson                    222   97269   96780   74066   74849   76006  173275    486   259
bson/jackson-manual                 223    9759    9516   12219   12449   12678   22436    495   278
bson/jackson-databind               229   11783   11416   14277   14506   14619   26403    519   293
bson/mongodb                        223    8923    8592   36600   35848   36001   44924    495   278
smile/jackson-manual                223    2418    2244    2679    2881    3016    5434    341   244
smile/jackson-databind              223    3574    3286    4283    4426    4565    8139    364   260
smile/protostuff-manual             230    2915    2748    2983    3271    3430    6345    325   233
smile/protostuff-runtime            224    3562    3288    3967    4336    4440    8003    339   240
xml/manual-woodstox                 223    7613    7256   11013   11156   11469   19082    653   304
xml/manual-aalto                    223    5004    4881    6512    6689    6888   11893    653   304
xml/manual-fastinfo                 229   14879   14558   15548   16242   16460   31339    377   284
xml/xstream+c                       230   15810   14567   46866   43983   47458   63268    487   244
xml/xstream+c-woodstox              229   13918   12543   26628   27342   27604   41522    525   273
xml/xstream+c-aalto                 230   11664   10202   19946   20570   20816   32481    525   273
xml/xstream+c-fastinfo              230   20343   18762   22019   22284   22607   42950    345   264
xml/jackson-databind/aalto          223    8175    7775   11367   11746   11863   20038    712   297
xml/javolution                      229   10445    9766   16627   16882   17195   27639    504   263
```

Columns:
  * create: create an object (using the classes specified by the serialization tool)
  * ser: create an object and serialize it
  * +same: serialize the same object (i.e. doesn't include creation time)
  * deser: deserialize an object
  * +shal: deserialize an object and access the top-level fields
  * +deep: deserialize an object and access all the fields
  * total: create + serialize + deserialize and access all fields
  * size: the size of the serialized data
  * +dfl: the size of the serialized data compressed with Java's built-in implementation of DEFLATE (zlib)