/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports.utilities;

import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sports.web.WebPages;

/**
 *
 * @author Lee
 */
@Component
public class CollectionUtilities {
    
    @Autowired
    private PropertyReader propertyReader;

    public CollectionUtilities() {
    }
    
    public List<List<WebPages>> getPartitionedWebPages(List<WebPages> pagesList) {
        double listSize = pagesList.size();
        double maxBrowsers = getMaxBrowsers();
        int max = (listSize > 0) ? (int) Math.ceil(listSize/maxBrowsers) : getMaxBrowsers();
        return Lists.partition(pagesList, max);
    }
    
    public int getMaxBrowsers() {
        return propertyReader.getIntegerProperty("max-browsers").get();
    }
}
