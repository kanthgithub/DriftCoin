package com.lorescianatico.driftcoin.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class BlockFactoryTest {

    @Test
    public void getBlock() {
        Block genesisBlock = BlockFactory.getBlock("0", "First block");
        logger.info("Hash for block 1 : " + genesisBlock.getHash());

        Block secondBlock = BlockFactory.getBlock(genesisBlock.getHash(), "Second block");
        logger.info("Hash for block 2 : " + secondBlock.getHash());

        Block thirdBlock = BlockFactory.getBlock(secondBlock.getHash(), "Third block");
        logger.info("Hash for block 3 : " + thirdBlock.getHash());
    }

    @Test
    public void getBlockChain() {
        BlockChain chain = BlockFactory.getBlockChain();
        assertNotNull(chain);
    }

    @Test
    public void getBlockChain1() {
        Block genesisBlock = BlockFactory.getBlock("0", "First block");
        logger.info("Hash for block 1 : " + genesisBlock.getHash());

        Block secondBlock = BlockFactory.getBlock(genesisBlock.getHash(), "Second block");
        logger.info("Hash for block 2 : " + secondBlock.getHash());

        Block thirdBlock = BlockFactory.getBlock(secondBlock.getHash(), "Third block");
        logger.info("Hash for block 3 : " + thirdBlock.getHash());

        BlockChain chain = BlockFactory.getBlockChain(genesisBlock, secondBlock, thirdBlock);
        assertTrue(chain.isChainValid());
        assertEquals(3, chain.size());
        assertFalse(chain.isEmpty());
    }

    @Test
    public void getBlockChainTampered() {
        Block genesisBlock = BlockFactory.getBlock("0", "First block");
        logger.info("Hash for block 1 : " + genesisBlock.getHash());

        Block secondBlock = BlockFactory.getBlock(genesisBlock.getHash(), "Second block");
        logger.info("Hash for block 2 : " + secondBlock.getHash());

        Block thirdBlock = BlockFactory.getBlock(secondBlock.getHash(), "Third block");
        logger.info("Hash for block 3 : " + thirdBlock.getHash());
        thirdBlock.setMessage("Fourth block");

        BlockChain chain = BlockFactory.getBlockChain(genesisBlock, secondBlock, thirdBlock);
        assertTrue(!chain.isChainValid());
        assertEquals(3, chain.size());
        assertFalse(chain.isEmpty());

    }
}