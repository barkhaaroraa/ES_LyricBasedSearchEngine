# 🎶 Lyric-Based Search Engine 🎶

A powerful search engine for finding song lyrics based on user input. The engine matches lyrics using **Exact Phrase Matching** and **Fuzzy Logic** techniques and ranks the results using custom algorithms like **Jaccard Score** and **TF-IDF**. The top results are retrieved efficiently using a **Priority Queue (Max Heap)**. 🔍

## 📋 Table of Contents
- [✨ Features](#features)
- [🛠️ Technologies Used](#technologies-used)
- [🔧 How It Works](#how-it-works)
- [📊 Data Flow](#data-flow)

## ✨ Features
- **Exact Phrase Match**: Matches lyrics with exact input for precise searches. 🎯
- **Fuzzy Logic Search**: Supports approximate matches for imperfect queries. 🧩
- **Custom Ranking Algorithms**:
  - **Jaccard Score**: Measures the similarity between song lyrics and the input. 🔗
  - **TF-IDF Score**: Assigns weights to lyrics based on frequency and rarity of words. 📊
- **Priority Queue (Max Heap)**: Efficiently sorts and returns the top 20 closest matching lyrics. 🏆
- **Google API Integration**: Extracts relevant words from the input text to enhance search results. 🌍

## 🛠️ Technologies Used
- **Elasticsearch**: For storing and querying lyrics. 🗃️
- **Java**: For backend processing and implementing custom ranking algorithms. ☕
- **Google API**: For extracting words and refining text input. 🧠
- **Max Heap**: For efficient sorting of search results. ⚖️
- **Priority Queue**: To manage top search results. 🚦

## 🔧 How It Works
1. **Data Processing**:
   - The user inputs lyrics either through text or voice. 📝🎤
   - The **Google API** extracts relevant words and refines the search query. 🔍
   
2. **Elasticsearch Integration**:
   - The lyrics database is indexed in **Elasticsearch**. 📚
   - Two types of searches are performed: 
     - **Exact Phrase Match** to find lyrics with the same sequence of words. 🧐
     - **Fuzzy Logic Search** for approximate matching. 🕵️‍♂️
   
3. **Custom Ranking**:
   - After the lyrics are fetched, they are ranked using two algorithms:
     - **Jaccard Score**: Compares the similarity of input lyrics with each song's lyrics. 🔄
     - **TF-IDF Score**: Assigns weight to the song based on the uniqueness and importance of words. ⚡
   
4. **Top Results**:
   - All possible matches are stored in an **ArrayList** and sorted using a **Priority Queue** (Max Heap). 🗂️
   - The **top 20 closest results** are displayed to the user. 🏅

## 📊 Data Flow
1. **Text Input** is processed via the **Google API** to extract key words. 🧑‍💻
2. The search is performed using both **Exact Phrase Match** and **Fuzzy Logic**. 🔄
3. Results are ranked using the **Jaccard Score** and **TF-IDF Score**. 📈
4. The ranked results are stored in a **Priority Queue (Max Heap)**. ⏳
5. **Top 20 Closest Results** are displayed. 🌟
 
![image](https://github.com/user-attachments/assets/1982cabb-0981-422a-8627-cbecea1a6d77)

