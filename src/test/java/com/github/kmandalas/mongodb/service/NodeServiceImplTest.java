package com.github.kmandalas.mongodb.service;

import com.github.kmandalas.mongodb.document.Node;
import com.github.kmandalas.mongodb.object.TreeNode;
import com.github.kmandalas.mongodb.repository.NodeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NodeServiceImplTest {

    @InjectMocks
    private NodeServiceImpl nodeService;

    @Mock
    private NodeRepository nodeRepository;

    @Test
    void getFullTree() throws Exception {
        final Node node = new Node();
        node.setChangesetId(1);
        node.setMasterId(NodeService.DEFAULT_ROOT_NODE_ID);
        node.setName("name1");

        when(nodeRepository.findDistinctByChangesetId(1)).thenReturn(Collections.singletonList(node));

        final TreeNode fullTree = nodeService.getFullTree(1);
        assertThat(fullTree)
                .isNotNull()
                .returns(1, from(TreeNode::getChangesetId))
                .returns("name1", from(TreeNode::getName));
    }
}