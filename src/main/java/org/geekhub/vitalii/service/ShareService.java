package org.geekhub.vitalii.service;

import org.geekhub.vitalii.dto.StockDTO;
import org.geekhub.vitalii.repository.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareService {

    private final ShareRepository shareRepository;
    private final int pageSize = 25;

    @Autowired
    public ShareService(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }

    public int getPageCount() {
        return (int) Math.ceil((double) shareRepository.sharesCount() / pageSize);
    }

    public List<StockDTO> getShares(Integer page) {
        int offset = (page - 1) * pageSize;
        return shareRepository.getShareInfo(pageSize, offset);
    }
}
