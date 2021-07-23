package com.ardublock.translator.block.carobot;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public abstract class AbstractCarobotWriteAnalogBlock extends TranslatorBlock {

  AbstractCarobotWriteAnalogBlock(Long blockId, Translator translator, String codePrefix,
      String codeSuffix, String label) {
    super(blockId, translator, codePrefix, codeSuffix, label);
    translator.addHeaderFile("CAROBOT_SwissCHEESE.h");
  }

  @Override
  public String toCode() throws SocketNullException, SubroutineNotDeclaredException {
    String ret = "analogWrite(";
    TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
    String outputPin = translatorBlock.toCode();
    ret = ret + outputPin;
    ret = ret + ", ";

    translator.addSetupCommand("pinMode(" + outputPin + ", OUTPUT);");
    translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
    ret = ret + translatorBlock.toCode();
    ret = ret + ");\n";
    return ret;
  }
}
