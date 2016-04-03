# Feature Comparison #

Performance numbers are useful, but they can be misleading.  For example:
  * some tools perform more safety checks than others -- these checks might be worth the performance hit
  * some tools will let you serialize your own hand-written Java classes; others require that you use Java classes generated from a schema file

This page tries to list the tool features that are relevant to interpreting our benchmarking results.  This is _not_ meant to be an exhaustive list of each tool's features.  For example, our "json/jackson" test abbreviated field names -- this doesn't mean that the Jackson JSON library requires the use of abbreviated field names.

<table border='1' cellspacing='0'>
<tr>
<blockquote><th>Tool</th>
<th>Language-Neutral</th>
<th>Data Structure</th>
<th>Serialization</th>
<th>Formats</th>
</tr>
<tr>
<td>java-built-in</td>
<td></td>
<td></td>
<td></td>
<td>binary</td>
</tr>
<tr>
<td>java-manual</td>
<td></td>
<td></td>
<td>manual</td>
<td>binary</td>
</tr>
<tr>
<td>scala/java-built-in</td>
<td></td>
<td></td>
<td></td>
<td>binary</td>
</tr>
<tr>
<td>scala/sbinary</td>
<td></td>
<td></td>
<td>manual</td>
<td>binary</td>
</tr>
<tr>
<td>kryo</td>
<td></td>
<td>optional annotate</td>
<td></td>
<td>binary</td>
</tr>
<tr>
<td>protobuf</td>
<td>yes</td>
<td>schema+gen</td>
<td></td>
<td>binary, json</td>
</tr>
<tr>
<td>thrift</td>
<td>yes</td>
<td>schema+gen</td>
<td></td>
<td>binary, json-like</td>
</tr>
<tr>
<td>protobuf/activemq-alt</td>
<td>yes</td>
<td>schema+gen</td>
<td></td>
<td>binary, json</td>
</tr>
<tr>
<td>protostuff</td>
<td>yes</td>
<td>schema+gen</td>
<td></td>
<td>binary, protobuf, json, xml, yaml</td>
</tr>
<tr>
<td>avro</td>
<td>yes</td>
<td>schema+gen</td>
<td></td>
<td>binary, json</td>
</tr>
<tr>
<td>json/jackson</td>
<td>yes</td>
<td></td>
<td>manual++</td>
<td>json+abbrev</td>
</tr>
<tr>
<td>json/jackson-databind</td>
<td>yes</td>
<td></td>
<td></td>
<td>json</td>
</tr>
<tr>
<td>xml/javolution</td>
<td>yes</td>
<td></td>
<td>manual</td>
<td>xml+abbrev</td>
</tr>
<tr>
<td>...xstream</td>
<td>yes</td>
<td></td>
<td></td>
<td>xml</td>
</tr>
<tr>
<td>...xstream+c</td>
<td>yes</td>
<td></td>
<td>manual</td>
<td>xml+abbrev</td>
</tr>
<tr>
<td>xml-fi/...</td>
<td>yes</td>
<td></td>
<td></td>
<td>binary xml</td>
</tr>
</table></blockquote>

**Language-neutral**: Whether the tool is a viable option if you want to be language-neutral.  For example, though it is _possible_ for other languages to consume Java's built-in serialization format, it is definitely not convenient -- that's why Java's built-in serialization format is not listed as being language-neutral.

The "yes" here just indicates some level of cross-language support.  Obviously the different tools have different levels of support for other languages.  You should probably do your own research to see if the tool has robust support for the language you need.

**Data structure**: Whether the tool places any restrictions on the classes it can serialize.  If this entry is empty, it means the tool will generally serialize any Java class.
  * schema+gen: you write a schema file (in the tool's schema language) and the tool _generates_ Java classes that you must use
  * annotate: the tool will let you use your own Java classes, but you need to annotate the Java classes to help the tool out
  * optional annotate: the tool can serialize any Java class, but you can improve or configure the serialization through annotations

The tools that generate code from a schema file vary a lot in the type of code they generate.  This affects the "create" time.
  * very simple classes that allow you to directly manipulate fields
  * classes with private fields and get/set methods
  * classes whose instances are immutable -- helps reduce certain kinds of bugs

The tools that generate code from a schema don't have equivalent schema languages.  Some schema languages are more expressive than others.  Some let you perform low-level optimizations.  Make sure you take a look at the different schema languages to see what each has to offer.

**Serialization**: How much additional work the programmer has to do to serialize data.
  * manual: The tool will take care of the low-level details of the format (like syntax) but you essentially have to write all the code to serialize your specific data structure.
  * manual++: The same thing as 'manual', but even more tedious.

**Formats**: The primary format is listed first.  The binary formats usually have a human-readable alternative format as well, used for specifying data values by hand or for debugging.
  * binary: some custom binary format.
  * json: the JSON format.
  * json-like: some custom text format that is similar to JSON/YAML/plist, etc.

# Tool Specifics #

java-built-in:
  * ObjectOutput.writeObject(java.io.Serializable)
  * Part of the reason it's slow is that it's the only test here that preserves arbitrary object graphs (all the other serializers flatten graphs to trees).  To do this, the serializer keeps track of every object's identity, which is an expensive operation.

java-manual:
  * Hand-written serialization code: [JavaManual.java, lines 25-144](http://code.google.com/p/thrift-protobuf-compare/source/browse/branches/kannan/tpc/src/serializers/JavaManual.java?r=206#25)

kryo:
  * We register each class with Kryo, an optional step that improves performance. [Kryo.java, lines 95-100](http://code.google.com/p/thrift-protobuf-compare/source/browse/branches/kannan/tpc/src/serializers/Kryo.java?r=206#95)
  * The "kryo-opt" test further improves performance by giving the serializer more information about the data values being serialized [Kryo.java, lines 105-130](http://code.google.com/p/thrift-protobuf-compare/source/browse/branches/kannan/tpc/src/serializers/Kryo.java?r=206#105)
  * Can be configured to use custom serialization code, but we use the default Kryo serialization code.

protobuf (Google Protocol Buffers):
  * The generated data classes are relatively heavy-weight.  Data values are immutable and are built by "Builder" objects that check the data for conformance to the schema.  This makes things safer and also accounts for why protobuf's "create" time is so high.
  * We set optimize\_for=SPEED
  * <a href='http://code.google.com/apis/protocolbuffers/docs/proto.html'>Schema language</a>

protobuf/activemq-alt:
  * Java-only protobuf-format-compatible implementation from the ActiveMQ project.
  * We use the "alternative" bindings generator, which parses the input on-demand (i.e. when the actual fields are accessed).

protostuff:
  * serialization api that enforces forward-backward compatibility for pojos
  * supported formats are: protostuff(native), protobuf, json, xml, yaml(ser only)
  * the regular variant serializes code-generated pojos whereas the runtime variant serializes existing pojos.
> > Note that there is a difference in serialized size for both variants because tpc/src/data/media/Media.java has an extra boolean field “hasBitrate” w/c explains the 2-byte difference(1-byte for the varint and 1-byte for the boolean value).

thrift (Apache Thrift):
  * Very similar to protobuf.
  * "thrift" uses the standard TBinaryProtocol serializer.
  * "thrift-compact" uses the newer TCompactProtocol serializer.
  * <a href='http://wiki.apache.org/thrift/Tutorial'>Schema language</a>

avro (Apache Avro):
  * <a href='http://hadoop.apache.org/avro/docs/current/spec.html'>Schema language</a>
  * Avro's data structures use UTF-8 encoded strings (instead of Java's native UTF-16 encoded strings).  Its "create" times and post-deserialize times are so high because the benchmark converts to/from native Java strings.  In actual use, careful programming may allow you to avoid these conversions.

json/jackson:
  * Lots of hand-written serialization code: [JsonJackson.java, lines 62-292](http://code.google.com/p/thrift-protobuf-compare/source/browse/branches/kannan/tpc/src/serializers/JsonJackson.java?r=206#62), with additional support code in [FieldMapping.java](http://code.google.com/p/thrift-protobuf-compare/source/browse/branches/kannan/tpc/src/data/media/FieldMapping.java?r=206)
  * Uses _abbreviated names_.
  * "json/jackson-databind" uses automatic serialization and full names.

xml/javolution:
  * Hand-written serialization code: [JavolutionXml.java](http://code.google.com/p/thrift-protobuf-compare/source/browse/branches/kannan/tpc/src/serializers/JavolutionXml.java?r=206)
  * Uses _abbreviated names_.

xstream:
  * The non-"+c" variants use XStream's built-in object-to-XML conversion (no extra code, full names).
  * The "+c" variants use a hand-written conversion function with _abbreviated names_. [XStream.java, lines 105-257](http://code.google.com/p/thrift-protobuf-compare/source/browse/branches/kannan/tpc/src/serializers/XStream.java?r=206#105)
  * "xml/xstream" uses XStream's own XML reader/writer.
  * The "xml/...-xstream" variants use various StAX implementations to do the XML reading/writing.