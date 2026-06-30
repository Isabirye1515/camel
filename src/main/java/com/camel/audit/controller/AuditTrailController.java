package com.camel.audit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.camel.audit.service.AuditTrailService;
import com.camel.audit.valueholder.AuditTrail;
import com.camel.common.paging.PageBean;
import com.camel.config.CommonConfig;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest")
public class AuditTrailController {

    @Autowired
    private AuditTrailService auditTrailService;

    @Autowired
    private CommonConfig commonConfig;

    @GetMapping("/trails")
    public ResponseEntity<PageBean<AuditTrail>> getAuditTrails(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "tableName", required = false) String tableName,
            HttpServletRequest request) throws Exception {

        int finalPageSize = (pageSize != null)
                ? pageSize
                : commonConfig.getPageSize();

        Map<String, Object> criteria = new HashMap<>();

        if (tableName != null && !tableName.trim().isEmpty()) {
            criteria.put("tableName", tableName);
        }

        int totalItems;
        List<AuditTrail> items;

        if (criteria.isEmpty()) {

            totalItems = auditTrailService.getCount();

            // Correct: page first, then page size
            items = auditTrailService.getPage(page, finalPageSize);

        } else {

            totalItems = auditTrailService.getCountMatching(criteria, false);

            // Optional: if you want paging while filtering,
            // you'll need a paged version of getAllMatching().
            items = auditTrailService.getAllMatching(criteria, false);
        }

        PageBean<AuditTrail> pageBean = new PageBean<>();
        pageBean.setPage(page);
        pageBean.setPageSize(finalPageSize);
        pageBean.setTotalItems(totalItems);
        pageBean.setTotalPages((int) Math.ceil((double) totalItems / finalPageSize));
        pageBean.setItems(items);

        pageBean.convertCreteriaToSearchMap(criteria);

        return ResponseEntity.ok(pageBean);
    }
}