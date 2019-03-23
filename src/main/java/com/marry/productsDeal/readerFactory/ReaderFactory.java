package com.marry.productsDeal.readerFactory;

import com.marry.productsDeal.utils.*;

import static com.marry.productsDeal.readerFactory.FileType.*;

/**
 * This class allows to choose specific reader
 * for getting information from different configuration files, according to their extensions
 */
public class ReaderFactory {

    /**
     * Get reader
     * @param extension file extension
     * @param filePath
     * @return
     */
    public static ProductsReader getReader(String extension, String filePath) {
        switch (extension) {
            case CSV:
                return new CsvProductReader(filePath);
            case JSON:
                return new JsonProductReader(filePath);
            case XML:
                return new XmlProductReader(filePath);
            case DB:
                return new DBProductReader(filePath);
            default:
                throw new RuntimeException("File is missing!");
        }

    }

}
