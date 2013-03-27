package pl.edu.pk.zpi.plagiator.core;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import pl.edu.pk.zpi.plagiator.core.extractor.BlockExtractor;

/**
 * Class analyzing two files
 * 
 * @author b4rt3k
 * 
 */
public class Analyzer {
	private TextProcessor processor = new TextProcessor();
	private StringBuilder substringBuilder = new StringBuilder();

	/**
	 * Class holds block indexes in pattern and source file (index mean word
	 * number from zero)
	 * 
	 * @author b4rt3k
	 * 
	 */
	public static class Pair {
		private int indexInPatternFile;
		private int indexInSourceFile;

		public int getIndexInPatternFile() {
			return indexInPatternFile;
		}

		public void setIndexInPatternFile(int indexInPatternFile) {
			this.indexInPatternFile = indexInPatternFile;
		}

		public int getIndexInSourceFile() {
			return indexInSourceFile;
		}

		public void setIndexInSourceFile(int indexInSourceFile) {
			this.indexInSourceFile = indexInSourceFile;
		}

		public Pair(int indexInPatternFile, int indexInSourceFile) {
			super();
			this.indexInPatternFile = indexInPatternFile;
			this.indexInSourceFile = indexInSourceFile;
		}

		public String toString() {
			return "{" + indexInPatternFile + ", " + indexInSourceFile + "}";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + indexInPatternFile;
			result = prime * result + indexInSourceFile;
			return result;
		}
	}

	/**
	 * Method finds given pattern in source file
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	private Collection<Pair> findPattern(int patternIndex, String pattern, String[] sourceFileArray, int blockLength) throws FileNotFoundException {
		List<Pair> output = new LinkedList<Pair>();
		int substringIndex = 0;
		String substring = buildSubstring(substringIndex, blockLength, sourceFileArray);
		while (!substring.isEmpty()) {
			if (processor.process(substring).equals(processor.process(pattern))) {
				output.add(new Pair(patternIndex, substringIndex));
			}
			substringIndex++;
			substring = buildSubstring(substringIndex, blockLength, sourceFileArray);
		}
		return output;
	}

	/**
	 * Method analyzes two files. We assume that files contains plain text
	 * before preprocessing (but without the quotes?)
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public Collection<Pair> analyze(File patternFile, File sourceFile, int blockLength) throws FileNotFoundException {
		BlockExtractor extr = new BlockExtractor(patternFile, blockLength);
		List<Pair> output = new LinkedList<Pair>();
		int patternIndex = 0;
		String[] sourceFileArray = fileToStringArray(sourceFile);
		while (extr.hasMoreBlocks()) {
			output.addAll(findPattern(patternIndex, extr.nextBlock(), sourceFileArray, blockLength));
			patternIndex += blockLength;
		}
		return output;
	}

	/**
	 * Method builds substring to analyze
	 * 
	 * @return
	 */
	private String buildSubstring(int substringIndex, int blockLength, String[] arr) {
		substringBuilder.setLength(0);
		int availableTokens = arr.length - substringIndex;
		int tokensToRead = blockLength > availableTokens ? availableTokens : blockLength;
		for (int i = substringIndex; i < (substringIndex + tokensToRead); i++) {
			substringBuilder.append(arr[i]).append(BlockExtractor.WORD_SEPARATOR);
		}
		return substringBuilder.toString();
	}

	/**
	 * Methods converting file to string array
	 * 
	 * @return
	 */
	private String[] fileToStringArray(File f) {
		String result = null;
		DataInputStream in = null;
		try {
			byte[] buffer = new byte[(int) f.length()];
			in = new DataInputStream(new FileInputStream(f));
			in.readFully(buffer);
			result = new String(buffer, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException("IO problem in fileToString", e);
		} finally {
			try {
				in.close();
			} catch (IOException e) { /* ignore it */
			}
		}
		return result.split(BlockExtractor.WORD_SEPARATOR);
	}
}
