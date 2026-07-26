# 📄 PDF Splitter

A simple **console-based PDF splitting utility** developed using **Java SE**, **Apache PDFBox**, and **NetBeans Ant**.

The application allows users to select a PDF file, specify multiple page ranges, and generate separate PDF files from those ranges.

---

## 🚀 Features

* 📂 Load an existing PDF file
* 📑 Display the total number of pages
* ✂️ Split PDF using custom page ranges
* 📁 Select a custom output directory
* 📄 Generate multiple PDF files in a single operation
* ✅ Validate PDF file and page ranges
* ⚠️ Handle invalid input and processing errors
* 💻 Fully console-based
* 🪶 Lightweight Java SE utility

---

## 🛠️ Technologies Used

* **Java SE**
* **Apache PDFBox 3.x**
* **NetBeans IDE**
* **Apache Ant**
* **Java I/O**
* **Java Collections / Utility APIs**

---

## 📁 Project Structure

```text
PDFSplitter/
│
├── nbproject/
│
├── src/
│   └── com/
│       └── vilva/
│           └── pdfsplitter/
│               ├── Main.java
│               └── PDFSplitter.java
│
├── test/
│
├── manifest.mf
│
└── README.md
```

---

## ⚙️ Requirements

Before running the project, make sure you have:

* Java JDK 8 or later
* NetBeans IDE
* Apache PDFBox 3.x

---

## 🔧 Setup

### 1. Clone the repository

```bash
git clone https://github.com/CodeVilva/PDFSplitter.git
```

### 2. Open in NetBeans

Open NetBeans and select:

```text
File → Open Project
```

Select the cloned `PDFSplitter` project.

### 3. Add Apache PDFBox

Download Apache PDFBox from the official website:

https://pdfbox.apache.org/download.html

In NetBeans:

```text
Right-click Project
→ Properties
→ Libraries
→ Compile
→ Add JAR/Folder
```

Add the required PDFBox JAR files.

### 4. Build and Run

Use:

```text
F6
```

or:

```text
Right-click Project → Run
```

---

## 🖥️ How It Works

The application follows a simple workflow:

```text
Start Application
       ↓
Enter PDF File Path
       ↓
Validate PDF
       ↓
Read Total Page Count
       ↓
Enter Output Directory
       ↓
Enter Number of Parts
       ↓
Enter Start & End Page
       ↓
Extract Selected Pages
       ↓
Generate Separate PDF
       ↓
Repeat Until All Parts Are Created
       ↓
Process Completed
```

---

## 📌 Example

Suppose the input PDF contains **12 pages**.

The user can split it as:

```text
Part 1 → Pages 1 - 4
Part 2 → Pages 5 - 8
Part 3 → Pages 9 - 12
```

The application generates:

```text
Split/
├── part_1.pdf
├── part_2.pdf
└── part_3.pdf
```

---

## 📸 Sample Console Output

```text
======================================
          PDF SPLITTER UTILITY
======================================

Enter PDF file path:
D:\PDFs\sample.pdf

PDF loaded successfully.
Total pages: 12

Enter output directory:
D:\PDFs\Split

How many parts do you want?
3

--- Part 1 ---
Start page: 1
End page: 4

Created: D:\PDFs\Split\part_1.pdf

--- Part 2 ---
Start page: 5
End page: 8

Created: D:\PDFs\Split\part_2.pdf

--- Part 3 ---
Start page: 9
End page: 12

Created: D:\PDFs\Split\part_3.pdf

======================================
       PDF SPLITTING COMPLETED
======================================
```

---

## 🧩 Main Components

### `Main.java`

Responsible for:

* Console interaction
* User input
* File validation
* Page range validation
* Output directory creation
* Error handling

### `PDFSplitter.java`

Responsible for:

* Loading PDF documents
* Reading page count
* Importing selected pages
* Creating new PDF documents
* Saving split PDF files

---

## 🔐 Privacy

This application processes PDF files **locally on the user's computer**.

No PDF files or extracted content are uploaded to an external server.

---

## 🔮 Future Improvements

Possible future versions may include:

* Split every page individually
* Split PDF every N pages
* Automatic output file naming
* Command-line arguments
* Progress indicator
* PDF metadata display
* Drag-and-drop file support
* Improved exception handling
* Optional GUI version

---

## 📚 Library

This project uses **Apache PDFBox**, an open-source Java library for working with PDF documents.

Official website:

https://pdfbox.apache.org/

---

## 👨‍💻 Author

**Vilva**

GitHub:
https://github.com/CodeVilva

---

## ⭐ Support

If you find this project useful, consider giving the repository a ⭐ on GitHub.
