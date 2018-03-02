package com.ardublock.translator.block.carobot;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;

public class CarobotOutputPortBlock extends TranslatorBlock {

  public CarobotOutputPortBlock(Long blockId, Translator translator, String codePrefix,
      String codeSuffix, String label) {
    super(blockId, translator, codePrefix, codeSuffix, label);
    translator.addHeaderFile("CAROBOT_SwissCHEESE.h");
    // translator.addSetupCommand("pinMode( " + label + ", OUTPUT);");
  }

  @Override
  public String toCode() throws SocketNullException {
    return codePrefix + label + codeSuffix;
  }

}
