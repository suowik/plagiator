package pl.edu.pk.zpi.plagiator.core;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import pl.edu.pk.zpi.plagiator.core.extractor.BlockExtractor;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Class analyzing two files
 *
 * @author b4rt3k
 */
@Component
public class Analyzer {
    private static final Logger logger = Logger.getLogger(Analyzer.class);
    private TextProcessor processor = new TextProcessor();
    private StringBuilder substringBuilder = new StringBuilder();

    /**
     * Method finds given pattern in source file
     *
     * @return
     * @throws FileNotFoundException
     */
    private Collection<MatchedTextBlocks> findPattern(int patternIndex, String pattern, String[] sourceFileArray, int blockLength) throws FileNotFoundException {
        List<MatchedTextBlocks> output = new LinkedList<>();
        int substringIndex = 0;
        String substring = buildSubstring(substringIndex, blockLength, sourceFileArray);
        while (!substring.isEmpty()) {
            if (processor.process(substring).equals(processor.process(pattern))) {
                logger.info("Match found: " + patternIndex + ", " + substringIndex +  " : " + pattern + ", " + substring);
                output.add(new MatchedTextBlocks(patternIndex, pattern, substringIndex, substring));
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
    public Collection<MatchedTextBlocks> analyze(String patternFile, String sourceFile, int blockLength) throws FileNotFoundException {
//		logger.info("Started comparison: " + patternFile.getName() + " and " + sourceFile.getName() + " with block length " + blockLength);
        BlockExtractor extr = new BlockExtractor(patternFile, blockLength);
        List<MatchedTextBlocks> output = new LinkedList<>();
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
    private String[] fileToStringArray(String wholeFile) {
        return wholeFile.split(BlockExtractor.WORD_SEPARATOR);
    }

}
