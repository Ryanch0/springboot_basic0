package com.apple.shop.item;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public  void saveItem(String title, Integer price){
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("Title must not be empty");
        }
        if (price == null || price < 0) {
            throw new IllegalArgumentException("Price must not be null and must be greater than or equal to 0");
        }
        Item addItem = new Item();
            addItem.setTitle(title);
            addItem.setPrice(price);
            itemRepository.save(addItem);
    }

    public List<Item> dbItem(){
         return itemRepository.findAll();
    }

    public Optional<Item> itemId(@PathVariable Integer id){
        return itemRepository.findById(id);
    }

    public void modifyItem(Integer id,String title, Integer price){
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("Title must not be empty");
        }
        if (price == null || price < 0) {
            throw new IllegalArgumentException("Price must not be null and must be greater than or equal to 0");
        }
        Item changeItem = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));
        //findById()는 Optional<Item>을 반환하므로, 찾는 id값이 없을때 예외처리 해주는 함수
        changeItem.setTitle(title);
        changeItem.setPrice(price);
        itemRepository.save(changeItem);
    }


    @Transactional
    public void deleteService(Integer id){
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()){
            itemRepository.deleteById(id);
        } else {
            throw new RuntimeException("Post not found with id: " + id);
        }
    }


}
