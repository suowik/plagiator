package pl.edu.pk.zpi.plagiator.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import pl.edu.pk.zpi.plagiator.core.extractor.BlockExtractor;

/**
 * Class analyzing two files
 * 
 * @author b4rt3k
 * 
 */
public class Analyzer {	
	private TextProcessor processor=new TextProcessor();

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
	 * @param pattern
	 * @param sourceFile
	 * @param blockLength
	 * @param comparator
	 * @return
	 * @throws FileNotFoundException
	 */
	private Collection<Pair> findPattern(int patternIndex, String pattern, File sourceFile, int blockLength) throws FileNotFoundException {
		BlockExtractor b1 = new BlockExtractor(sourceFile, blockLength);
		Set<Pair> output = new HashSet<Pair>();
		int substringIndex = 0;
		while (b1.hasMoreBlocks()) {			
			if (processor.process(b1.nextBlock(1)).equals(processor.process(pattern))) {
				output.add(new Pair(patternIndex, substringIndex));
			}
			substringIndex++;
		}
		return output;
	}

	/**
	 * Method analyzes two files. We assume that files contains plain text
	 * before preprocessing (but without the quotes?)
	 * 
	 * @param patternFile
	 * @param sourceFile
	 * @param blockLength
	 * @param comparator
	 * @return
	 * @throws FileNotFoundException
	 */
	public Collection<Pair> analyze(File patternFile, File sourceFile, int blockLength) throws FileNotFoundException {
		BlockExtractor extr = new BlockExtractor(patternFile, blockLength);
		Set<Pair> output = new HashSet<Pair>();
		int patternIndex = 0;
		while (extr.hasMoreBlocks()) {
			output.addAll(findPattern(patternIndex, extr.nextBlock(), sourceFile, blockLength));
			patternIndex += blockLength;
		}
		return output;
	}
}
