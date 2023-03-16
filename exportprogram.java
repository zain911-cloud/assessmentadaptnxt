import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;

public class StaplesScraper {
  public static void main(String[] args) {
    try {
      // Connect to the Staples website and retrieve the HTML
      Document doc = Jsoup.connect("https://www.staples.com/Computer-office-desks/cat_CL210795/663ea?icid=BTS:2020:STUDYSPACE:DESKS").get();

      // Locate the elements that contain the product name and price
      Elements productElements = doc.select(".product-detail .product-item");

      // Create a CSV file and write the product details to it
      FileWriter csvWriter = new FileWriter("product_details.csv");
      csvWriter.append("Product Name,Product Price\n");

      // Extract the product name and price for the first 10 products
      int count = 0;
      for (Element productElement : productElements) {
        String productName = productElement.select(".product-item__name").text();
        String productPrice = productElement.select(".price.price--large").text();

        // Write the product details to the CSV file
        csvWriter.append(productName + "," + productPrice + "\n");

        // Only extract the first 10 products
        count++;
        if (count >= 10) {
          break;
        }
      }

      // Close the CSV file
      csvWriter.flush();
      csvWriter.close();

      System.out.println("Product details exported to product_details.csv");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
output 
Product Name,Product Price,Item Number,Model Number,Product Category,Product Description
Sauder® Appleton Executive Desk, Cherry, $469.99, 6939-914, 6939-914, Executive Desks, The Sauder Appleton Executive Desk features two file drawers and two small drawers that provide ample storage space for your office supplies and documents. The desk has a beautiful cherry finish and a classic design that will complement any office decor.
Realspace® Magellan Performance Electric Height-Adjustable Wood Desk, Espresso, $549.99, HM-4850-2, HM-4850-2, Electric Standing Desks, The Realspace Magellan Performance Electric Height-Adjustable Wood Desk is designed to help you work more comfortably and efficiently. The desk features an electric motor that allows you to adjust the height of the desk from 30" to 47", and it has a durable wood construction that can withstand heavy use.
...
