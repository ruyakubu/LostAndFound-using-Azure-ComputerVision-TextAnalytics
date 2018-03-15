# LostAndFound-using-Azure-ComputerVision-TextAnalytics

 This code illustrates how to use Java, Computer Vision and Text Analysis to automate a Lost/Found system. 
 The first scenario provides a solution where an item is found and a picture is taken of it and store in a Azure Blob Storage and the JSON metadata
 is stored in Azure Cosmos DB. Next, it shows how Computer Vision returns metadata about visual content found in an image
 (e.g. tagging, descriptions, colors, adult/racy restrictions etc).
 
 Finally, it uses a scenario where the owner of the item submits a Lost/Found report, and Azure Text Analytics automatically 
 extracts key phrases to search if the item was found.  To do this, it you uses Azure Search Services to index data in the Cosmos DB and uses Lucene Fuzzy searchs for identity a match base of the key phrase.
 
 #Prerequisties
 
 - Create an Azure Storage account.  Create a Blob container for the images.  The copy the Keys -> "Connectin String" and paste in the jave code
 - Create an Aure  Computer Vision API. The copy the Keys and paste in the Java Code
 - Create an Aure  the the (No SQL) Cosmos DB instance. An store the JSON results in the DB.
 - Create an Azure Search Service and chose the Cosmos datasource to create an indexer aganist it.
   E.g. https://docs.microsoft.com/en-us/azure/search/search-create-index-portal
