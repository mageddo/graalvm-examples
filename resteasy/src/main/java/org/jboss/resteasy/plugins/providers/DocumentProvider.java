package org.jboss.resteasy.plugins.providers;

import org.jboss.resteasy.plugins.server.servlet.ResteasyContextParameters;
import org.jboss.resteasy.resteasy_jaxrs.i18n.LogMessages;
import org.jboss.resteasy.spi.ReaderException;
import org.jboss.resteasy.spi.ResteasyConfiguration;
import org.jboss.resteasy.spi.WriterException;
import org.w3c.dom.Document;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Provider that reads and writes org.w3c.dom.Document.
 *
 * @author <a href="sduskis@gmail.com">Solomon Duskis</a>
 * @version $Revision: $
 */
@Provider
@Produces({"text/xml", "text/*+xml", "application/xml", "application/*+xml"})
@Consumes({"text/xml", "text/*+xml", "application/xml", "application/*+xml"})
public class DocumentProvider implements MessageBodyReader<Object>, MessageBodyWriter<Object>  {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void writeTo(Object o, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
		throw new UnsupportedOperationException();
	}
}
