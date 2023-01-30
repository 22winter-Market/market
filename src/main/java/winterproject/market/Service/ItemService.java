package winterproject.market.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import winterproject.market.Repository.ItemRepository;
import winterproject.market.Repository.MemberRepository;
import winterproject.market.controller.ItemForm;
import winterproject.market.domain.Item;
import winterproject.market.domain.Member;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ItemService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public Long itemUpload(ItemForm itemForm) {
        Member member = memberRepository.findOneById(itemForm.getMemberId());
        Item item = new Item(member, itemForm);
        itemRepository.save(item);
        return item.getId();
    }

    public void itemDelete(Long id) {
        itemRepository.delete(id);
    }

    public void changeStatus(Long id, String status) {
        Item item = itemRepository.findById(id);
        item.soldOut();
    }

    public Item findItemById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> findItems(Member member) {
        return itemRepository.findAllByMember(member);
    }
}
