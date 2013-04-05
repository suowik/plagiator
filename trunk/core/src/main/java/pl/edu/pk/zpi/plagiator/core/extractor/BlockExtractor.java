package pl.edu.pk.zpi.plagiator.core.extractor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Class for extracting n word blocks from given file
 * 
 * @author b4rt3k
 * 
 */
public class BlockExtractor {
	private Scanner tokenizer;
	private final String WORD_SEPARATOR = " ";
	private final int MAX_BLOCK_LENGTH = 10;
	private int blockLength;
	private StringBuilder blockBuilder;
	Queue<String> cache;
	private boolean cacheReady = false;
	String currentBlock;

	/**
	 * Constructor receives file and word count per block
	 * 
	 * @param file
	 * @param n
	 * @throws FileNotFoundException
	 */
	public BlockExtractor(File file, int n) throws FileNotFoundException {
		if (n > MAX_BLOCK_LENGTH || n < 1) {
			throw new RuntimeException("Too long block lenght. Current: " + n + ". Min allowed: 1" + ". Max allowed: " + MAX_BLOCK_LENGTH);
		}
		this.blockLength = n;
		this.cache = new LinkedList<String>();
		this.tokenizer = new Scanner(file, "UTF-8");
		this.tokenizer.useDelimiter(WORD_SEPARATOR);
		this.blockBuilder = new StringBuilder();
	}

	/**
	 * Method check if more blocks are available
	 * 
	 * @return
	 */
	public boolean hasMoreBlocks() {
		return tokenizer.hasNext();
	}

	/**
	 * Method returns next block using given shift.
	 * 
	 * @param shift
	 * @return
	 */
	public String nextBlock(int shift) {
		if (shift < 0) {
			throw new RuntimeException("Shift cannot be negative.");
		}
		int counter = 0;
		int tokensToRead = shift != 0 && cacheReady ? shift : blockLength;
		while (tokenizer.hasNext() && counter < tokensToRead) {
			if (cache.size() >= blockLength) {
				cache.poll();
			}
			cache.offer(tokenizer.next());
			counter++;
		}
		/**
		 * last block may be shorter, so its needed to shorten cache to avoid
		 * duplicates
		 */
		for (int i = 0; i < tokensToRead - counter; i++) {
			cache.poll();
		}
		cacheReady = true;
		return currentBlock = buildToken(cache);
	}

	public String nextBlock() {
		return nextBlock(0);
	}

	/**
	 * Method returns current block
	 * 
	 * @return
	 */
	public String currentBlock() {
		return currentBlock;
	}

	/**
	 * Method builds token from cache queue
	 * 
	 * @param cache
	 * @return
	 */
	private String buildToken(Queue<String> cache) {
		blockBuilder.setLength(0);
		for (String item : cache) {
			blockBuilder.append(item).append(WORD_SEPARATOR);
		}
		return blockBuilder.toString();
	}
}