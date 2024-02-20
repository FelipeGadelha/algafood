package br.com.portfolio.algafood.config;

public sealed interface Expr {
    record SumExpr(Expr left, Expr right) implements Expr { }
    record ProdExpr(Expr left, Expr right) implements Expr { }
    record NegExpr(Expr e) implements Expr { }
    record ConstExpr(int c) implements Expr { }


}

//int eval(Expr e) {
//    return switch (e) {
//        case SumExpr(var a, var b) -> eval(a) + eval(b);
//        case ProdExpr(var a, var b) -> eval(a) * eval(b);
//        case NegExpr(var a) -> -eval(a);
//        case ConstExpr(var i) -> i;
//    }
//}