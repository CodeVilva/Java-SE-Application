# Word to PDF Converter

A console-based Java application that converts Microsoft Word (`.docx`) documents into PDF format. This project is built with **Maven** for dependency management, making it easy to build and run while handling required libraries automatically.

## Features

- Convert `.docx` files to PDF
- Console-based user interaction
- Fast and reliable document conversion
- Input validation and error handling
- Maven-based dependency management

## Technologies Used

- Java SE
- Maven
- Apache POI
- PDF Conversion Library
- Core Java

## Project Structure

```
Word-To-PDF-Converter/
│
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
├── pom.xml
├── README.md
└── .gitignore
```

## Prerequisites

- Java JDK 17 or later *(or your project's JDK version)*
- Apache Maven 3.8+

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/<your-username>/Word-To-PDF-Converter.git
cd Word-To-PDF-Converter
```

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn exec:java
```

> Alternatively, import the project into an IDE such as IntelliJ IDEA, Eclipse, or NetBeans, which will automatically download the Maven dependencies.

## Example

**Input**

```
Enter the path to the Word document:
C:\Users\User\Documents\Report.docx
```

**Output**

```
Converting document...
Conversion completed successfully!

Output file:
Report.pdf
```

## Future Improvements

- Batch conversion of multiple documents
- Support for legacy `.doc` files
- Custom output directory
- Password-protected PDF generation
- Preserve advanced formatting and embedded media

## License

This project is licensed under the MIT License.
