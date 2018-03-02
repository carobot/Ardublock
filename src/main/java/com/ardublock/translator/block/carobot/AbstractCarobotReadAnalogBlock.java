package com.ardublock.translator.block.carobot;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.NumberBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public abstract class AbstractCarobotReadAnalogBlock extends TranslatorBlock {

  AbstractCarobotReadAnalogBlock(Long blockId, Translator translator, String codePrefix,
      String codeSuffix, String label) {
    super(blockId, translator, codePrefix, codeSuffix, label);
    translator.addHeaderFile("CAROBOT_SwissCHEESE.h");
  }

  @Override
  public String toCode() throws SocketNullException, SubroutineNotDeclaredException {
    String ret = "analogRead(";
    TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
    if (translatorBlock instanceof NumberBlock) {
      ret = ret + "A";
      ret = ret + translatorBlock.toCode();
      ret = ret + ")";
      return codePrefix + ret + codeSuffix;
    } else {
      if (translatorBlock instanceof CarobotInputPortBlock) {
        ret = ret + translatorBlock.toCode();
        ret = ret + ")";
        return codePrefix + ret + codeSuffix;
      } else {
        throw new BlockException(blockId, "analog pin# must be a number");
      }
    }
  }

}
