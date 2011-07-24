package serializers;

import javax.management.RuntimeErrorException;

import org.msgpack.MessagePack;

import serializers.java.MediaContent;

public class MessagePackSerializer extends StdMediaSerializer
{

	private MediaContent fixture;

	protected MessagePackSerializer() {
		super("MessagePack");
		MessagePack.register(MediaContent.class);
		try {
			fixture = create();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public MediaContent deserialize(byte[] array) throws Exception {
		MediaContent unpack = MessagePack.unpack(array, MediaContent.class);
		// Note: hack due to a bug in MessagePack causing it to fail serializing the _copyright field
		unpack._media.setCopyright(fixture._media.getCopyright());
		return unpack;
	}

	@Override
	public byte[] serialize(MediaContent content) throws Exception {
		return MessagePack.pack(content);
	}
	
	public static void main(String[] args) throws Exception {
		MessagePackSerializer serializer = new MessagePackSerializer();
		serializer.deserialize(serializer.serialize(serializer.create()));
	}

}
