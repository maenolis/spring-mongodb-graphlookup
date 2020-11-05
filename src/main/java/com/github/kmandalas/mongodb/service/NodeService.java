package com.github.kmandalas.mongodb.service;

import com.github.kmandalas.mongodb.exception.NotFoundException;
import com.github.kmandalas.mongodb.object.TreeNode;
import org.springframework.util.CollectionUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface NodeService {

    int DEFAULT_ROOT_NODE_ID = -1;

    TreeNode getFullTree(int changesetId) throws NotFoundException;

    void deleteFullTree(int changesetId) throws NotFoundException;

    TreeNode getSubTree(int changesetId, int nodeId) throws NotFoundException;

    void deleteSubTree(int changesetId, int nodeId) throws NotFoundException;

    static TreeNode assembleTree(final List<TreeNode> nodes, final int rootNodeId) {
        final Map<Integer, TreeNode> mapTmp = new LinkedHashMap<>();
        // Save all nodes to a map
        for (final TreeNode current : nodes) {
            mapTmp.put(current.getMasterId(), current);
        }
        // Loop and assign parent/child relationships
        for (final TreeNode current : nodes) {
            final List<Integer> parents = current.getParentId();

            if (!CollectionUtils.isEmpty(parents)) {
                for (final Integer pid : parents) {
                    final TreeNode parent = mapTmp.get(pid);
                    if (parent != null) {
                        parent.addChild(current);
                        current.addParent(parent);
                    }
                }
            }
        }
        return mapTmp.get(rootNodeId);
    }
}
