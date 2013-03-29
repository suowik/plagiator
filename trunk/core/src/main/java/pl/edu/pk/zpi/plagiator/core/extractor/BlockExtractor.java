package pl.edu.pk.zpi.plagiator.core.extractor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * Class for extracting n word blocks from given file
 * 
 * @author b4rt3k
 * 
 */
public class BlockExtractor {
	private static final Logger logger = Logger.getLogger(BlockExtractor.class);
	private Scanner tokenizer;
	public static final String WORD_SEPARATOR = " ";
	private final int MAX_BLOCK_LENGTH = 10;
	private int blockLength;
	private StringBuilder blockBuilder;

	/**
	 * Constructor receives file and word count per block
	 * 
	 * @param file
	 * @param n
	 * @throws FileNotFoundException
	 */
	public BlockExtractor(File file, int n) throws FileNotFoundException {
		if (n > MAX_BLOCK_LENGTH || n < 1) {
			String msg = "Too long block lenght. Current: " + n + ". Min allowed: 1" + ". Max allowed: " + MAX_BLOCK_LENGTH;
			logger.error(msg);
			throw new RuntimeException(msg);
		}
		this.blockLength = n;
		this.tokenizer = new Scanner(file, "UTF-8");
		this.tokenizer.useDelimiter(WORD_SEPARATOR);
		this.blockBuilder = new StringBuilder();
		logger.info("Block extractor created successfully.");
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
	 * Method returns next block.
	 * 
	 * @return
	 */
	public String nextBlock() {
		int counter = 0;
		blockBuilder.setLength(0);
		int tokensToRead = blockLength;
		while (tokenizer.hasNext() && counter < tokensToRead) {
			blockBuilder.append(tokenizer.next()).append(WORD_SEPARATOR);
			counter++;
		}
		return blockBuilder.toString();
	}
}