package org.encryfoundation.prismlang.codec

import org.encryfoundation.prismlang.core.Types._
import org.encryfoundation.prismlang.core.Ast._
import scodec.Codec
import scodec.codecs.{Discriminated, uint2, uint4, uint8}


object ScriptCodec {

  import scodec.codecs.implicits._

  implicit def dT = Discriminated[PType, Int](uint8)
  implicit def dAny = dT.bind[PAny.type](0)
  implicit def dUnit = dT.bind[PUnit.type](1)
  implicit def dInt = dT.bind[PInt.type](2)
  implicit def dBool = dT.bind[PBoolean.type](3)
  implicit def dString = dT.bind[PString.type](4)
  implicit def dByte = dT.bind[PByte.type](5)
  implicit def dColl = dT.bind[PCollection](6)
  implicit def dOpt = dT.bind[POption](7)
  implicit def dFunc = dT.bind[PFunc](8)
  implicit def dTuple = dT.bind[PTuple](9)
  implicit def dObj = dT.bind[BaseObject.type](10)
  implicit def dStruct = dT.bind[Struct](11)
  implicit def dEncryProof = dT.bind[EncryProof.type](12)
  implicit def dEncryContract = dT.bind[EncryContract.type](13)
  implicit def dSig = dT.bind[Signature25519.type](14)
  implicit def dProp = dT.bind[EncryProposition.type](15)
  implicit def dMulSig = dT.bind[MultiSig.type](16)
  implicit def dEncryBox = dT.bind[EncryBox.type](17)
  implicit def dAssetBox = dT.bind[AssetBox.type](18)
  implicit def dAiBox = dT.bind[AssetIssuingBox.type](19)
  implicit def dDBox = dT.bind[DataBox.type](20)
  implicit def dUnlocker = dT.bind[EncryUnlocker.type](21)
  implicit def dTransact = dT.bind[EncryTransaction.type](22)
  implicit def dState = dT.bind[EncryState.type](23)
  implicit def dNit = dT.bind[Nit.type](24)

  implicit def dExp = Discriminated[Expr, Int](uint8)
  implicit def dCont = dExp.bind[Expr.Contract](0)
  implicit def dSch = dExp.bind[Expr.Schema](1)
  implicit def dBlc = dExp.bind[Expr.Block](2)
  implicit def dLet = dExp.bind[Expr.Let](3)
  implicit def dDef = dExp.bind[Expr.Def](4)
  implicit def dLamb = dExp.bind[Expr.Lambda](5)
  implicit def dIf = dExp.bind[Expr.If](6)
  implicit def dIfLet = dExp.bind[Expr.IfLet](7)
  implicit def dBoolOp = dExp.bind[Expr.Bool](8)
  implicit def dBin = dExp.bind[Expr.Bin](9)
  implicit def dUnary = dExp.bind[Expr.Unary](10)
  implicit def dCompOp = dExp.bind[Expr.Compare](11)
  implicit def dName = dExp.bind[Expr.Name](12)
  implicit def dCall = dExp.bind[Expr.Call](13)
  implicit def dAttr = dExp.bind[Expr.Attribute](14)
  implicit def dSubscr = dExp.bind[Expr.Subscript](15)
  implicit def dIntConst = dExp.bind[Expr.IntConst](16)
  implicit def dStr = dExp.bind[Expr.Str](17)
  implicit def dCollConst = dExp.bind[Expr.Collection](18)
  implicit def dTupleConst = dExp.bind[Expr.Tuple](19)
  implicit def dBase58Str = dExp.bind[Expr.Base58Str](20)
  implicit def dBase16Str = dExp.bind[Expr.Base16Str](21)
  implicit def dTrue = dExp.bind[Expr.True.type](22)
  implicit def dFalse = dExp.bind[Expr.False.type](23)
  implicit def dSizeOf = dExp.bind[Expr.SizeOf](24)
  implicit def dExists = dExp.bind[Expr.Exists](25)
  implicit def dSum = dExp.bind[Expr.Sum](26)
  implicit def dMap = dExp.bind[Expr.Map](27)

  implicit def dNode = Discriminated[Node, Int](uint2)
  implicit def dModule = dNode.bind[Module](0)

  implicit def dSliceOp = Discriminated[SliceOp, Int](uint2)
  implicit def dSlice = dSliceOp.bind[SliceOp.Slice](0)
  implicit def dIdx = dSliceOp.bind[SliceOp.Index](1)

  implicit def dBooleanOp = Discriminated[BooleanOp, Int](uint2)
  implicit def dAnd = dBooleanOp.bind[BooleanOp.And.type](0)
  implicit def dOr = dBooleanOp.bind[BooleanOp.Or.type](1)

  implicit def dOperator = Discriminated[Operator, Int](uint4)
  implicit def dAdd = dOperator.bind[Operator.Add.type](0)
  implicit def dSub = dOperator.bind[Operator.Sub.type](1)
  implicit def dMult = dOperator.bind[Operator.Mult.type](2)
  implicit def dDiv = dOperator.bind[Operator.Div.type](3)
  implicit def dMod = dOperator.bind[Operator.Mod.type](4)
  implicit def dPow = dOperator.bind[Operator.Pow.type](5)

  implicit def dUnaryOp = Discriminated[UnaryOp, Int](uint2)
  implicit def dInv = dUnaryOp.bind[UnaryOp.Invert.type](0)
  implicit def dNot = dUnaryOp.bind[UnaryOp.Not.type](1)

  implicit def dCompare = Discriminated[CompOp, Int](uint4)
  implicit def dEq = dCompare.bind[CompOp.Eq.type](0)
  implicit def dNotEq = dCompare.bind[CompOp.NotEq.type](1)
  implicit def dLt = dCompare.bind[CompOp.Lt.type](2)
  implicit def dLtE = dCompare.bind[CompOp.LtE.type](3)
  implicit def dGt = dCompare.bind[CompOp.Gt.type](4)
  implicit def dGtE = dCompare.bind[CompOp.GtE.type](5)
  implicit def dIs = dCompare.bind[CompOp.Is.type](6)
  implicit def dIsNot = dCompare.bind[CompOp.IsNot.type](7)
  implicit def dIn = dCompare.bind[CompOp.In.type](8)
  implicit def dNotIn = dCompare.bind[CompOp.NotIn.type](9)

  implicit def dTypeDesc = Discriminated[TypeDescriptor, Int](uint2)
  implicit def dSimpleT = dTypeDesc.bind[TypeDescriptor.SimpleType](0)
  implicit def dProdT = dTypeDesc.bind[TypeDescriptor.ProductType](1)

  val exprCodec: Codec[Expr] = Codec[Expr]
}
