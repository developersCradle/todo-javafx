package datamodel;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TodoData {

    private static final String TODOS_FILE = "todos.xml";
    
    private static final String TODO = "todo"; //tag inside xml that defines todo
    
    private static final String DONE = "done";//Is it done, boolean
    private static final String TASK = "task";//Task to be done
    private static final String DATE = "date";//Deadline

    private ObservableList<Todo> todos;

    public TodoData() {
        todos = FXCollections.observableArrayList();
    }
    
	public ObservableList<Todo> getTodos() {
		return todos;
	}

    public void addTodo(Todo todo ) {
        getTodos().add(todo);
    } 

    public void deleteTodo(Todo todo) {
        getTodos().remove(todo);
    }

    public void loadContacts() {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            
            File file = new File(TODOS_FILE);
            System.out.println(file.getCanonicalPath());
            InputStream in = new FileInputStream(TODOS_FILE);
            
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            Todo todo = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a todo item, we create a new todo
                    if (startElement.getName().getLocalPart().equals(TODO)) {
                        todo = new Todo();
                        continue;
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(DONE)) {
                            event = eventReader.nextEvent();
                            todo.setDone(Boolean.parseBoolean((event.asCharacters().getData())));
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals(TASK)) {
                        event = eventReader.nextEvent();
                        todo.setTask(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(DATE)) {
                        event = eventReader.nextEvent();
                        todo.setDate((Date)new SimpleDateFormat("dd/MM/yyyy").parse(event.asCharacters().getData()));
                        continue;
                    }
                }

                // If we reach the end of a contact element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(TODO)) {
                        getTodos().add(todo);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }

//    public void saveContacts() {
//
//        try {
//            // create an XMLOutputFactory
//            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
//            // create XMLEventWriter
//            XMLEventWriter eventWriter = outputFactory
//                    .createXMLEventWriter(new FileOutputStream(TODOS_FILE));
//            // create an EventFactory
//            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
//            XMLEvent end = eventFactory.createDTD("\n");
//            // create and write Start Tag
//            StartDocument startDocument = eventFactory.createStartDocument();
//            eventWriter.add(startDocument);
//            eventWriter.add(end);
//
//            StartElement contactsStartElement = eventFactory.createStartElement("",
//                    "", "contacts");
//            eventWriter.add(contactsStartElement);
//            eventWriter.add(end);
//
//            for (Contact contact: getTodos()) {
//                saveContact(eventWriter, eventFactory, contact);
//            }
//
//            eventWriter.add(eventFactory.createEndElement("", "", "contacts"));
//            eventWriter.add(end);
//            eventWriter.add(eventFactory.createEndDocument());
//            eventWriter.close();
//        }
//        catch (FileNotFoundException e) {
//            System.out.println("Problem with Contacts file: " + e.getMessage());
//            e.printStackTrace();
//        }
//        catch (XMLStreamException e) {
//            System.out.println("Problem writing contact: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    private void saveContact(XMLEventWriter eventWriter, XMLEventFactory eventFactory, Contact contact)
//            throws FileNotFoundException, XMLStreamException {
//
//        XMLEvent end = eventFactory.createDTD("\n");
//
//        // create contact open tag
//        StartElement configStartElement = eventFactory.createStartElement("",
//                "", CONTACT);
//        eventWriter.add(configStartElement);
//        eventWriter.add(end);
//        // Write the different nodes
//        createNode(eventWriter, FIRST_NAME, contact.getFirstName());
//        createNode(eventWriter, LAST_NAME, contact.getLastName());
//        createNode(eventWriter, PHONE_NUMBER, contact.getPhoneNumber());
//        createNode(eventWriter, NOTES, contact.getNotes());
//
//        eventWriter.add(eventFactory.createEndElement("", "", CONTACT));
//        eventWriter.add(end);
//    }
//
//    private void createNode(XMLEventWriter eventWriter, String name,
//                            String value) throws XMLStreamException {
//
//        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
//        XMLEvent end = eventFactory.createDTD("\n");
//        XMLEvent tab = eventFactory.createDTD("\t");
//        // create Start node
//        StartElement sElement = eventFactory.createStartElement("", "", name);
//        eventWriter.add(tab);
//        eventWriter.add(sElement);
//        // create Content
//        Characters characters = eventFactory.createCharacters(value);
//        eventWriter.add(characters);
//        // create End node
//        EndElement eElement = eventFactory.createEndElement("", "", name);
//        eventWriter.add(eElement);
//        eventWriter.add(end);
//    }



}
