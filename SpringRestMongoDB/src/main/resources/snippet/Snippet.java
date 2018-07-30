package snippet;

public class Snippet {
	<?xml version="1.0"?>
	<wsdl:definitions name="HelloWorld"
	  targetNamespace="http://codenotfound.com/services/helloworld"
	  xmlns:tns="http://codenotfound.com/services/helloworld" xmlns:types="http://codenotfound.com/types/helloworld"
	  xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
	
	  <wsdl:types>
	    <xsd:schema targetNamespace="http://codenotfound.com/types/helloworld"
	      xmlns:xsd="http://www.w3.org/2001/XMLSchema" elementFormDefault="qualified"
	      attributeFormDefault="unqualified" version="1.0">
	
	      <xsd:element name="person">
	        <xsd:complexType>
	          <xsd:sequence>
	            <xsd:element name="firstName" type="xsd:string" />
	            <xsd:element name="lastName" type="xsd:string" />
	          </xsd:sequence>
	        </xsd:complexType>
	      </xsd:element>
	
	      <xsd:element name="greeting">
	        <xsd:complexType>
	          <xsd:sequence>
	            <xsd:element name="greeting" type="xsd:string" />
	          </xsd:sequence>
	        </xsd:complexType>
	      </xsd:element>
	    </xsd:schema>
	  </wsdl:types>
	
	  <wsdl:message name="SayHelloInput">
	    <wsdl:part name="person" element="types:person" />
	  </wsdl:message>
	
	  <wsdl:message name="SayHelloOutput">
	    <wsdl:part name="greeting" element="types:greeting" />
	  </wsdl:message>
	
	  <wsdl:portType name="HelloWorld_PortType">
	    <wsdl:operation name="sayHello">
	      <wsdl:input message="tns:SayHelloInput" />
	      <wsdl:output message="tns:SayHelloOutput" />
	    </wsdl:operation>
	  </wsdl:portType>
	
	  <wsdl:binding name="HelloWorld_SoapBinding" type="tns:HelloWorld_PortType">
	    <soap:binding style="document"
	      transport="http://schemas.xmlsoap.org/soap/http" />
	    <wsdl:operation name="sayHello">
	      <soap:operation
	        soapAction="http://codenotfound.com/services/helloworld/sayHello" />
	      <wsdl:input>
	        <soap:body use="literal" />
	      </wsdl:input>
	      <wsdl:output>
	        <soap:body use="literal" />
	      </wsdl:output>
	    </wsdl:operation>
	  </wsdl:binding>
	
	  <wsdl:service name="HelloWorld_Service">
	    <wsdl:documentation>Hello World service</wsdl:documentation>
	    <wsdl:port name="HelloWorld_Port" binding="tns:HelloWorld_SoapBinding">
	      <soap:address location="http://localhost:9090/codenotfound/ws/helloworld" />
	    </wsdl:port>
	  </wsdl:service>
	
	</wsdl:definitions>
}

