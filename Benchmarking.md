# We moved to a new home #

The wiki moved to [http://wiki.github.com/eishay/jvm-serializers/](http://wiki.github.com/eishay/jvm-serializers/)

For discussions please use [http://groups.google.com/group/java-serialization-benchmarking](http://groups.google.com/group/java-serialization-benchmarking)


# Intro #
Started with <a href='http://www.eishay.com/search/label/protobuf'>few blog posts</a> and with the help of many contributes, this project is now benchmarking much more then just protobuf and thrift. Thanks to all who looked at the code, contributed, suggested and pointed bugs. Three major contributions are from <a href='http://www.cowtowncoder.com/blog/blog.html'>cowtowncoder</a> who fixed the <a href='http://stax.codehaus.org/'>stax</a> code, <a href='http://www.samsarin.com'>Chris Pettitt</a> who added the <a href='http://www.json.org/'>json</a> code and <a href='http://github.com/davidB'>David Bernard</a> for the xstream and java <a href='http://java.sun.com/j2se/1.3/docs/api/java/io/Externalizable.html'>externalizable</a>.
The charts below are displaying the latest results. Note that the charts are scaled to best fit the results and they might be misleading in come cases. If you wish to see the numbers scroll down to the chart at the end of the page. Overall we have benchmarks for [protobuf](http://code.google.com/p/protobuf/), [thrift](http://incubator.apache.org/thrift/), [java](http://java.sun.com/j2se/1.4.2/docs/api/java/io/Serializable.html), [scala](http://www.scala-lang.org/), few implementations of [stax](http://stax.codehaus.org/), [binaryxml](http://download.oracle.com/docs/cd/B28359_01/appdev.111/b28394/adx_j_xmlbin.htm), [json](http://www.json.org/java/), [xstream](http://xstream.codehaus.org/), [javolution](http://javolution.org/), [hessian](http://hessian.caucho.com/), [avro](http://hadoop.apache.org/avro/), [sbinary](http://code.google.com/p/sbinary/), [JSON Marshaller](http://code.google.com/p/jsonmarshaller/), and [Kryo](http://code.google.com/p/kryo/).

# Numbers are not everything #
Benchmarks can be very misleading. Different hardware, use cases, and/or datasets will provide different results and sometimes a marginal performance boost is eclipsed by other features like <a href='http://www.eishay.com/2009/04/protocol-buffers-forward-backward.html'>forward and backward compatibility</a>, cross language support, simpler API, and more.

# Charts #

## Setup ##
The following measurements were performed with revision <a href='http://code.google.com/p/thrift-protobuf-compare/source/browse/?r=128'><a href='https://code.google.com/p/thrift-protobuf-compare/source/detail?r=128'>r128</a></a> on Windows 7 64-bit using Sun's JVM 1.6.0\_15 JRE 32-bit, with an Intel Core i7 920 CPU. Note the tests are run with a JVM heap size of 16MB and using the server HotSpot compiler.

Omitted from the first three charts: json/google-gson and scala. These serializers are so slow, they would break the scale of our charts. See below for the naked data.

## Total Time ##
Including creating an object, serializing and deserializing:

<img src='http://chart.apis.google.com/chart?chtt=totalTime&chf=c||lg||0||FFFFFF||1||76A4FB||0|bg||s||EFEFEF&chs=689x430&chd=t:5917.121999999999,6911.219,7746.0375,10105.623,11598.1135,11702.6325,12798.176,13289.6525,13308.289,14413.6315,15197.342,15251.716,16006.7985,18152.156499999997,20703.491,21125.9925,29864.972999999998,34437.3555,42352.662,65733.528,97347.103&chds=0,107081.81330000001&chxt=y&chxl=0:|java|JsonMarshaller|xstream (stax with conv)|hessian|binaryxml/FI|json/jackson-databind|stax/woodstox|javolution xmlformat|protostuff-json|thrift|protostuff-numeric-json|stax/aalto|json (jackson)|sbinary|avro-generic|activemq protobuf|protobuf|avro-specific|kryo|kryo-optimized|java (externalizable)&chm=N *f*,000000,0,-1,10&lklk&chdlp=t&chco=660000|660033|660066|660099|6600CC|6600FF|663300|663333|663366|663399|6633CC|6633FF|666600|666633|666666&cht=bhg&chbh=10&nonsense=aaa.png' />

## Serialization Time ##
Serializing with a new object each time (object creation time included):

<img src='http://chart.apis.google.com/chart?chtt=timeSerializeDifferentObjects&chf=c||lg||0||FFFFFF||1||76A4FB||0|bg||s||EFEFEF&chs=689x430&chd=t:2874.1185,2912.7375,3437.8375,5749.9665,6330.785,6777.6865,7223.819,7226.509,7302.9785,7584.8375,8011.9495,8018.866,8542.4285,8639.84,8704.781,10550.4115,13354.7855,15336.6385,16116.891,24618.395,25773.3065&chds=0,28350.637150000002&chxt=y&chxl=0:|java|JsonMarshaller|xstream (stax with conv)|binaryxml/FI|hessian|json/jackson-databind|sbinary|protostuff-json|stax/woodstox|avro-generic|protostuff-numeric-json|javolution xmlformat|thrift|protobuf|json (jackson)|activemq protobuf|stax/aalto|avro-specific|kryo|kryo-optimized|java (externalizable)&chm=N *f*,000000,0,-1,10&lklk&chdlp=t&chco=660000|660033|660066|660099|6600CC|6600FF|663300|663333|663366|663399|6633CC|6633FF|666600|666633|666666&cht=bhg&chbh=10&nonsense=aaa.png' />

## Deserialization Time ##
Often the most expensive operation. To make a fair comparison, all fields of the deserialized instances are accessed - this forces lazy deserializers to really do their work. The raw data below shows additional measurements for deserialization.

<img src='http://chart.apis.google.com/chart?chtt=timeDeserializeAndCheckAllFields&chf=c||lg||0||FFFFFF||1||76A4FB||0|bg||s||EFEFEF&chs=689x430&chd=t:3043.0035,3998.4815,4308.2,4355.6565,4371.6045,4584.8715,4779.31,4924.946,6084.47,7185.3925,7366.9585,7948.7375,8082.8465,10567.319,10575.581,12161.0625,14528.3345,21082.57,26235.771,41115.133,71573.7965&chds=0,78731.17615&chxt=y&chxl=0:|java|JsonMarshaller|xstream (stax with conv)|hessian|binaryxml/FI|stax/woodstox|json/jackson-databind|javolution xmlformat|stax/aalto|thrift|protostuff-json|protostuff-numeric-json|json (jackson)|activemq protobuf|avro-generic|sbinary|protobuf|avro-specific|kryo|kryo-optimized|java (externalizable)&chm=N *f*,000000,0,-1,10&lklk&chdlp=t&chco=660000|660033|660066|660099|6600CC|6600FF|663300|663333|663366|663399|6633CC|6633FF|666600|666633|666666&cht=bhg&chbh=10&nonsense=aaa.png' />

## Serialization Size ##
May vary a lot depending on number of repetitions in lists, usage of number compacting in protobuf, strings vs numerics, assumptions that can be made about the object graph, and more. Interesting point is Scala and Java which holds the name of the classes in the serialized form. I.e. longer class names = larger serialized form. In Scala its worse since the Scala compiler creates more implicit classes then java.

<img src='http://chart.apis.google.com/chart?chtt=length&chf=c||lg||0||FFFFFF||1||76A4FB||0|bg||s||EFEFEF&chs=689x430&chd=t:207.0,211.0,211.0,226.0,231.0,231.0,264.0,264.0,300.0,353.0,359.0,370.0,378.0,399.0,419.0,448.0,465.0,470.0,475.0,475.0,526.0,919.0,2024.0&chds=0,2226.4&chxt=y&chxl=0:|scala|java|hessian|stax/woodstox|stax/aalto|json/google-gson|json/jackson-databind|protostuff-json|javolution xmlformat|xstream (stax with conv)|json (jackson)|JsonMarshaller|protostuff-numeric-json|thrift|binaryxml/FI|sbinary|java (externalizable)|protobuf|activemq protobuf|kryo|avro-specific|avro-generic|kryo-optimized&chm=N *f*,000000,0,-1,10&lklk&chdlp=t&chco=660000|660033|660066|660099|6600CC|6600FF|663300|663333|663366|663399|6633CC|6633FF|666600|666633|666666&cht=bhg&chbh=10&nonsense=aaa.png' />

## Object Creation Time ##
Object creation is not so meaningful since it takes in average 100 nano to create an object. The surprise comes from <a href='http://code.google.com/p/protobuf/'>protobuf</a> which takes a very long time to create an object. Its the only point in this set of benchmarks where it didn't perform as well as <a href='http://incubator.apache.org/thrift/'>thrift</a>. Scala (and to a lesser point - java) on the other hand is fast, seems like its a good language to handle in memory data structures but when coming to serialization you might want to check the alternatives.

<img src='http://chart.apis.google.com/chart?chtt=timeCreate&chf=c||lg||0||FFFFFF||1||76A4FB||0|bg||s||EFEFEF&chs=689x430&chd=t:125.51285,126.19877,168.21827,168.80043,169.048285,170.108855,170.740975,171.880325,173.08692,173.28482,173.569175,173.82471,174.35308,174.39919,175.258025,175.75949,227.9755,254.11328,470.51276,475.759915,479.06845,2643.18237,4024.12346&chds=0,4426.535806&chxt=y&chxl=0:|avro-generic|avro-specific|protostuff-numeric-json|protostuff-json|protobuf|activemq protobuf|thrift|json (jackson)|javolution xmlformat|binaryxml/FI|xstream (stax with conv)|stax/aalto|json/jackson-databind|json/google-gson|stax/woodstox|JsonMarshaller|java (externalizable)|kryo-optimized|kryo|java|hessian|sbinary|scala&chm=N *f*,000000,0,-1,10&lklk&chdlp=t&chco=660000|660033|660066|660099|6600CC|6600FF|663300|663333|663366|663399|6633CC|6633FF|666600|666633|666666&cht=bhg&chbh=10&nonsense=aaa.png' />

# Numbers #
Times are in nanoseconds, sizes are in bytes.
```
                        ,   Object create,       Serialize,  /w Same Object,     Deserialize, and Check Media,   and Check All,      Total Time, Serialized Size
avro-generic            ,      4024.12346,      8018.86600,      4030.37150,      4779.31000,      4779.31000,      4779.31000,     12798.17600,        211
avro-specific           ,      2643.18237,      5749.96650,      3204.01250,      4355.65650,      4355.65650,      4355.65650,     10105.62300,        211
activemq protobuf       ,       254.11328,      6777.68650,        71.48850,        14.60200,      2574.77900,      4924.94600,     11702.63250,        231
protobuf                ,       470.51276,      7226.50900,      3698.94400,      3478.95350,      3826.13550,      4371.60450,     11598.11350,        231
thrift                  ,       227.97550,      7302.97850,      7165.79000,      7948.73750,      7948.73750,      7948.73750,     15251.71600,        353
hessian                 ,       168.21827,     13354.78550,     12849.28050,     21082.57000,     21082.57000,     21082.57000,     34437.35550,        526
kryo                    ,       169.04829,      3437.83750,      3297.00450,      4308.20000,      4308.20000,      4308.20000,      7746.03750,        226
kryo-optimized          ,       170.10886,      2912.73750,      2813.59000,      3998.48150,      3998.48150,      3998.48150,      6911.21900,        207
java                    ,       168.80043,     25773.30650,     25136.38350,     71573.79650,     71573.79650,     71573.79650,     97347.10300,        919
java (externalizable)   ,       170.74098,      2874.11850,      2674.87900,      3043.00350,      3043.00350,      3043.00350,      5917.12200,        264
scala                   ,       125.51285,     62838.27450,     61814.38950,    194495.92550,    194495.92550,    194495.92550,    257334.20000,       2024
json (jackson)          ,       175.75949,      7223.81900,      7113.92050,      6084.47000,      6084.47000,      6084.47000,     13308.28900,        378
json/jackson-databind   ,       173.56918,     10550.41150,     10443.20400,     10575.58100,     10575.58100,     10575.58100,     21125.99250,        465
JsonMarshaller          ,       171.88033,     24618.39500,     24488.50800,     41115.13300,     41115.13300,     41115.13300,     65733.52800,        370
protostuff-json         ,       475.75992,      8639.84000,      8069.39750,      7366.95850,      7366.95850,      7366.95850,     16006.79850,        448
protostuff-numeric-json ,       479.06845,      8011.94950,      7405.96250,      7185.39250,      7185.39250,      7185.39250,     15197.34200,        359
json/google-gson        ,       173.28482,    449118.35900,    449995.44750,    491268.32050,    491268.32050,    491268.32050,    940386.67950,        470
stax/woodstox           ,       173.08692,      8542.42850,      8408.70150,     12161.06250,     12161.06250,     12161.06250,     20703.49100,        475
stax/aalto              ,       173.82471,      6330.78500,      6159.79150,      8082.84650,      8082.84650,      8082.84650,     14413.63150,        475
binaryxml/FI            ,       174.39919,     15336.63850,     15210.98450,     14528.33450,     14528.33450,     14528.33450,     29864.97300,        300
xstream (stax with conv),       174.35308,     16116.89100,     15564.12000,     26235.77100,     26235.77100,     26235.77100,     42352.66200,        399
javolution xmlformat    ,       175.25803,      7584.83750,      7446.11950,     10567.31900,     10567.31900,     10567.31900,     18152.15650,        419
sbinary                 ,       126.19877,      8704.78100,      8743.20950,      4584.87150,      4584.87150,      4584.87150,     13289.65250,        264
```