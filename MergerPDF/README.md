# MergePDF

A simple console-based Java utility application that combines multiple PDF files into a single PDF document. The application is designed to make PDF merging quick and straightforward through a command-line interface.

## Features

- Merge multiple PDF files into one document
- Select PDF files using file paths
- Specify the output PDF location
- Console-based interface
- Input validation
- Error handling
- Preserves the original order of selected PDF files

## Technologies Used

- Java SE
- Core Java
- Java I/O
- Java NIO
- PDF Processing Library

## Project Structure

```text
MergePDF/
│
├── src/
│   └── ...
├── README.md
└── .gitignore
```

## Prerequisites

- Java JDK 8 or later
- Required PDF processing library

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/<your-username>/MergePDF.git
cd MergePDF
```

### Compile and Run

Compile the Java source files using the Java compiler:

```bash
javac *.java
```

Run the application:

```bash
java Main
```

> Replace `Main` with the name of your main class if your application uses a different class name.

## How It Works

1. Start the application.
2. Enter the number of PDF files you want to merge.
3. Provide the path of each PDF file.
4. Specify the destination path for the merged PDF.
5. The application processes the files in the order provided.
6. A new PDF containing all selected documents is generated.

## Example

### Input

```text
Enter number of PDF files: 3

Enter PDF 1 path:
C:\Documents\File1.pdf

Enter PDF 2 path:
C:\Documents\File2.pdf

Enter PDF 3 path:
C:\Documents\File3.pdf

Enter output PDF path:
C:\Documents\Merged.pdf
```

### Output

```text
Merging PDF files...

PDF files merged successfully!

Output:
C:\Documents\Merged.pdf
```

## Use Cases

- Combine multiple reports into one PDF
- Merge project documents
- Combine invoices or receipts
- Create a single document from multiple PDF chapters
- Consolidate scanned documents

## Future Improvements

- Drag-and-drop file selection
- GUI version
- PDF page reordering
- Page range selection
- PDF compression
- Password-protected PDF support
- Batch PDF merging
- PDF preview

## License

This project is licensed under the MIT License.
