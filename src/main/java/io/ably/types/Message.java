package io.ably.types;

import java.io.IOException;

import io.ably.util.Serialisation;

/**
 * A class representing an individual message to be sent or received
 * via the Ably Realtime service.
 */
public class Message extends BaseMessage {

	/**
	 * The event name, if available
	 */
	public String name;

	/**
	 * Construct a message from a JSON-encoded response body.
	 * @param packed: the response data
	 * @return
	 */
	public static Message fromJSON(String packed) throws AblyException {
		try {
			return Serialisation.jsonObjectMapper.readValue(packed, Message.class);
		} catch (IOException ioe) {
			throw AblyException.fromIOException(ioe);
		}
	}

	/**
	 * Construct a message from a Msgpack-encoded response body.
	 * @param packed: the response data
	 * @return
	 */
	public static Message fromMsgpack(byte[] packed) throws AblyException {
		try {
			return Serialisation.msgpackObjectMapper.readValue(packed, Message.class);
		} catch (IOException ioe) {
			throw AblyException.fromIOException(ioe);
		}
	}

	/**
	 * Default constructor
	 */
	public Message() {}

	/**
	 * Construct a message from event name and data 
	 * @param name
	 * @param data
	 */
	public Message(String name, Object data) {
		this(name, null, data);
	}

	/**
	 * Generic constructor
	 * @param name
	 * @param data
	 */
	public Message(String name, String clientId, Object data) {
		this.name = name;
		this.clientId = clientId;
		this.data = data;
	}

	/**
	 * Generate a String summary of this Message
	 * @return string
	 */
	public String toString() {
		StringBuilder result = new StringBuilder("[Message");
		super.getDetails(result);
		if(name != null)
			result.append(" name=").append(name);
		result.append(']');
		return result.toString();
	}
}
