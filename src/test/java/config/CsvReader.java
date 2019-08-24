package config;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import com.opencsv.CSVReader;

public class CsvReader {

	public Iterator<String[]> getTestDataFromCsv() throws IOException {  //List<String[]>
		String path = "testData.csv";
		Reader reader = new FileReader(path);
		CSVReader csvreader = new CSVReader(reader);
		List<String[]> list = csvreader.readAll();
		csvreader.close();
		Iterator<String[]> ite = list.iterator();
		return ite;
	}
}