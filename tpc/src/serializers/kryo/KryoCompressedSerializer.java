
package serializers.kryo;

import serializers.java.MediaContent;

import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.compress.DeflateCompressor;

/**
 * This shows how to compress the data with Kryo, but isn't really useful to compare to other serializers.
 */
public class KryoCompressedSerializer extends KryoOptimizedSerializer {
	public KryoCompressedSerializer () {
		Serializer mediaContentSerializer = kryo.getRegisteredClass(MediaContent.class).getSerializer();
		kryo.register(MediaContent.class, new DeflateCompressor(mediaContentSerializer));
	}

	public String getName () {
		return "kryo-compressed";
	}
}
