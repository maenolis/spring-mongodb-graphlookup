package com.github.kmandalas.mongodb.controller;

import com.github.kmandalas.mongodb.object.TreeNode;
import com.github.kmandalas.mongodb.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class NodeController {

    @Autowired
    NodeService nodeService;

    @GetMapping(value = "/{changesetId}")
    public ResponseEntity<TreeNode> getFullTree(@PathVariable("changesetId") int changesetId) throws Exception {
        return ResponseEntity.ok(nodeService.getFullTree(changesetId));
    }

    @DeleteMapping(value = "/{changesetId}")
    public ResponseEntity<Void> deleteFullTree(@PathVariable("changesetId") int changesetId) throws Exception {
        nodeService.deleteFullTree(changesetId);
        return ResponseEntity.<Void>noContent().build();
    }

    @GetMapping(value = "/{changesetId}/st/{nodeId}")
    public ResponseEntity<TreeNode> getSubtree(@PathVariable("changesetId") int changesetId, @PathVariable("nodeId") int nodeId) throws Exception {
        return ResponseEntity.ok(nodeService.getSubTree(changesetId, nodeId));
    }

    @DeleteMapping(value = "/{changesetId}/st/{nodeId}")
    public ResponseEntity<Void> deleteSubtree(@PathVariable("changesetId") int changesetId, @PathVariable("nodeId") int nodeId) throws Exception {
        nodeService.deleteSubTree(changesetId, nodeId);
        return ResponseEntity.<Void>noContent().build();
    }

}
